package com.robertx22.uncommon.utilityclasses;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public final class WizardryUtilities {

    /**
     * Returns whether the block at the given coordinates can be replaced by another
     * one (works as if a block is being placed by a player). True for air, liquids,
     * vines, tall grass and snow layers but not for flowers, signs etc. This is a
     * shortcut for
     * <code>world.getBlockState(pos).getMaterial().isReplaceable()</code>.
     *
     * @see WizardryUtilities#canBlockBeReplacedB(World, BlockPos)
     */
    public static boolean canBlockBeReplaced(World world, BlockPos pos) {
        return world.isAirBlock(new BlockPos(pos)) || world.getBlockState(pos)
                .getMaterial()
                .isReplaceable();
    }

    /**
     * Returns whether the block at the given coordinates can be replaced by another
     * one (works as if a block is being placed by a player) and is not a liquid.
     * True for air, vines, tall grass and snow layers but not for flowers, signs
     * etc. or any liquids.
     *
     * @see WizardryUtilities#canBlockBeReplaced(World, BlockPos)
     */
    public static boolean canBlockBeReplacedB(World world, BlockPos pos) {
        return canBlockBeReplaced(world, pos) && !world.getBlockState(pos)
                .getMaterial()
                .isLiquid();
    }

    /**
     * Returns whether the block at the given coordinates is unbreakable in survival
     * mode. In vanilla this is true for bedrock and end portal frame, for example.
     * This is a shortcut for world.getBlockState(pos).getBlockHardness(world, pos)
     * == -1.0f. Not much of a shortcut any more, since block ids have been phased
     * out.
     */
    public static boolean isBlockUnbreakable(World world, BlockPos pos) {
        return world.isAirBlock(new BlockPos(pos)) ? false : world.getBlockState(pos)
                .getBlockHardness(world, pos) == -1.0f;
    }

    /**
     * Finds the nearest floor level to the given y coord within the range specified
     * at the given x and z coords. Everything that is not air is treated as floor,
     * even stuff that can't be walked on.
     *
     * @param world
     * @param x     The x coordinate to search in
     * @param y     The y coordinate to search from
     * @param z     The z coordinate to search in
     * @param range The maximum distance from the given y coordinate to search.
     * @return The y coordinate of the closest floor level, or -1 if there is none.
     * Returns the actual level of the floor as would be seen in the debug
     * screen when the player is standing on it.
     * @see WizardryUtilities#getNearestFloorLevel(World, BlockPos, int)
     */
    public static int getNearestFloorLevelC(World world, BlockPos pos, int range) {
        int yCoord = -2;
        for (int i = -range; i <= range; i++) {
            if (world.isAirBlock(new BlockPos(pos.up(i + 1))) && (i < yCoord - pos.getY() || yCoord == -2)) {
                // The last bit determines whether the block found to be a suitable floor is
                // closer than the previous
                // one found.
                yCoord = pos.getY() + i;
            }
        }
        return yCoord + 1;
    }

    /**
     * Gets the blockstate of the block the specified entity is standing on. Uses
     * {@link MathHelper#floor_double(double)} because casting to int will not
     * return the correct coordinate when x or z is negative.
     */
    public static IBlockState getBlockEntityIsStandingOn(Entity entity) {
        BlockPos pos = new BlockPos(MathHelper.floor(entity.posX), (int) entity.getBoundingBox().minY - 1, MathHelper
                .floor(entity.posZ));
        return entity.world.getBlockState(pos);
    }

    /**
     * Shorthand for
     * {@link WizardryUtilities#getEntitiesWithinRadius(double, double, double, double, World, Class)}
     * with EntityLivingBase as the entity type. This is by far the most common use
     * for that method, which is why this shorthand exists.
     *
     * @param radius The search radius
     * @param x      The x coordinate to search around
     * @param y      The y coordinate to search around
     * @param z      The z coordinate to search around
     * @param world  The world to search in
     */
    public static List<EntityLivingBase> getEntitiesWithinRadius(double radius, double x,
                                                                 double y, double z,
                                                                 World world) {
        return getEntitiesWithinRadius(radius, x, y, z, world, EntityLivingBase.class);
    }

    /**
     * Returns all entities of the specified type within the specified radius of the
     * given coordinates. This is different to using a raw AABB because a raw AABB
     * will search in a cube volume rather than a sphere. Note that this does not
     * exclude any entities; if any specific entities are to be excluded this must
     * be checked when iterating through the list.
     *
     * @param radius     The search radius
     * @param x          The x coordinate to search around
     * @param y          The y coordinate to search around
     * @param z          The z coordinate to search around
     * @param world      The world to search in
     * @param entityType The class of entity to search for; pass in Entity.class for
     *                   all entities
     * @see {@link WizardryUtilities#getEntitiesWithinRadius(double, double, double, double, World)}
     */
    public static <T extends Entity> List<T> getEntitiesWithinRadius(double radius,
                                                                     double x, double y,
                                                                     double z,
                                                                     World world,
                                                                     Class<T> entityType) {
        AxisAlignedBB aabb = new AxisAlignedBB(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
        List<T> entityList = world.getEntitiesWithinAABB(entityType, aabb);
        for (int i = 0; i < entityList.size(); i++) {
            if (entityList.get(i).getDistance(x, y, z) > radius) {
                entityList.remove(i);
            }
        }
        return entityList;
    }

    public static <T extends Entity> List<T> getEntitiesWithinRadius(double radius,
                                                                     Entity entity,
                                                                     Class<T> entityType) {
        double x = entity.posX;
        double y = entity.posY;
        double z = entity.posZ;
        AxisAlignedBB aabb = new AxisAlignedBB(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
        List<T> entityList = entity.world.getEntitiesWithinAABB(entityType, aabb);
        for (int i = 0; i < entityList.size(); i++) {
            if (entityList.get(i).getDistance(x, y, z) > radius) {
                entityList.remove(i);
            }
        }
        return entityList;
    }

    /**
     * Gets an entity from its UUID. Note that you should check this isn't null. If
     * the UUID is known to belong to an EntityPlayer, use the more efficient
     * {@link World#getPlayerEntityByUUID(UUID)} instead.
     *
     * @param world The world the entity is in
     * @param id    The entity's UUID
     * @return The Entity that has the given UUID, or null if no such entity exists
     * in the specified world.
     */
    @Nullable
    public static Entity getEntityByUUID(World world, UUID id) {

        for (Entity entity : world.loadedEntityList) {
            // This is a perfect example of where you need to use .equals() and not ==. For
            // most applications,
            // this was unnoticeable until world reload because the UUID instance or entity
            // instance is stored.
            // Fixed now though.
            if (entity.getUniqueID().equals(id)) {
                return entity;
            }
        }
        return null;
    }

    // No point allowing anything other than players for these methods since other
    // entities can use Entity#playSound.

    /**
     * Shortcut for
     * {@link World#playSound(EntityPlayer, double, double, double, SoundEvent, SoundCategory, float, float)}
     * where the player is null but the x, y and z coordinates are those of the
     * passed in player. Use in preference to
     * {@link EntityPlayer#playSound(SoundEvent, float, float)} if there are
     * client-server discrepancies.
     */
    public static void playSoundAtPlayer(EntityPlayer player, SoundEvent sound,
                                         SoundCategory category, float volume,
                                         float pitch) {
        player.world.playSound(null, player.posX, player.posY, player.posZ, sound, category, volume, pitch);
    }

    /**
     * See
     * {@link WizardryUtilities#playSoundAtPlayer(EntityPlayer, SoundEvent, SoundCategory, float, float)}.
     * Category defaults to {@link SoundCategory#PLAYERS}.
     */
    public static void playSoundAtPlayer(EntityPlayer player, SoundEvent sound,
                                         float volume, float pitch) {
        player.world.playSound(null, player.posX, player.posY, player.posZ, sound, SoundCategory.PLAYERS, volume, pitch);
    }

    /**
     * Returns the entity riding the given entity, or null if there is none. Allows
     * for neater code now that entities have a list of passengers, because it is
     * necessary to check that the list is not null or empty first.
     */
    @Nullable
    public static Entity getRider(Entity entity) {
        return entity.getPassengers() != null && !entity.getPassengers()
                .isEmpty() ? entity.getPassengers().get(0) : null;
    }

    /**
     * Attacks the given entity with the given damage source and amount, but
     * preserving the entity's original velocity instead of applying knockback, as
     * would happen with
     * {@link EntityLivingBase#attackEntityFrom(DamageSource, float)} <i>(More
     * accurately, calls that method as normal and then resets the entity's velocity
     * to what it was before).</i> Handy for when you need to damage an entity
     * repeatedly in a short space of time.
     *
     * @param entity The entity to attack
     * @param source The source of the damage
     * @param amount The amount of damage to apply
     * @return True if the attack succeeded, false if not.
     */
    public static boolean attackEntityWithoutKnockback(Entity entity, DamageSource source,
                                                       float amount) {
        double vx = entity.motionX;
        double vy = entity.motionY;
        double vz = entity.motionZ;
        boolean succeeded = entity.attackEntityFrom(source, amount);
        entity.motionX = vx;
        entity.motionY = vy;
        entity.motionZ = vz;
        return succeeded;
    }

    /**
     * Applies the standard (non-enchanted) amount of knockback to the given target,
     * using the same calculation as
     * {@link EntityLivingBase#attackEntityFrom(DamageSource, float)}. Use in
     * conjunction with
     * {@link WizardryUtilities#attackEntityWithoutKnockback(Entity, DamageSource, float)}
     * to change the source of knockback for an attack.
     *
     * @param attacker The entity that caused the knockback; the target will be
     *                 pushed away from this entity.
     * @param target   The entity to be knocked back.
     */
    public static void applyStandardKnockback(Entity attacker, EntityLivingBase target) {
        double dx = attacker.posX - target.posX;
        double dz;
        for (dz = attacker.posZ - target.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math
                .random()) * 0.01D) {
            dx = (Math.random() - Math.random()) * 0.01D;
        }
        // The first argument is never used.
        target.knockBack(null, 0.4f, dx, dz);
    }

    // Just what benefit does having posY be the eye position on the first person
    // client actually give?

    /**
     * Gets the y coordinate of the given player's eyes. This is to cover an
     * inconsistency between the value of EntityPlayer.posY on the first person
     * client and everywhere else; in first person (i.e. when
     * Minecraft.getInstance().player == player) player.posY is the eye position,
     * but everywhere else it is the feet position. This is intended for use when
     * spawning particles, since this is the only situation where the discrepancy is
     * likely to matter.
     * <p>
     * As of Wizardry 1.2, this is just a shorthand for:
     * <p>
     * <code><center>player.getBoundingBox().minY + player.getEyeHeight()</code></center>
     */
    public static double getPlayerEyesPos(EntityLivingBase player) {
        return player.getBoundingBox().minY + player.getEyeHeight();
    }

    /**
     * Returns a list of the itemstacks in the given player's hotbar. Defined here
     * for convenience and to centralise the (unfortunately unavoidable) use of
     * hardcoded numbers to reference the inventory slots. The returned list is a
     * modifiable copy of part of the player's inventory stack list; as such,
     * changes to the list are <b>not</b> written through to the player's inventory.
     * However, the ItemStack instances themselves are not copied, so changes to any
     * of their fields (size, metadata...) will change those in the player's
     * inventory.
     *
     * @since Wizardry 1.2
     */
    public static List<ItemStack> getHotbar(EntityPlayer player) {
        NonNullList<ItemStack> hotbar = NonNullList.create();
        hotbar.addAll(player.inventory.mainInventory.subList(0, 9));
        return hotbar;
    }

    /**
     * Returns a list of the itemstacks in the given player's hotbar and offhand,
     * sorted into the following order: main hand, offhand, rest of hotbar
     * left-to-right. The returned list is a modifiable copy of part of the player's
     * inventory stack list; as such, changes to the list are <b>not</b> written
     * through to the player's inventory. However, the ItemStack instances
     * themselves are not copied, so changes to any of their fields (size,
     * metadata...) will change those in the player's inventory.
     *
     * @since Wizardry 1.2
     */
    public static List<ItemStack> getPrioritisedHotbarAndOffhand(EntityPlayer player) {
        List<ItemStack> hotbar = WizardryUtilities.getHotbar(player);
        // Adds the offhand item to the beginning of the list so it is processed before
        // the hotbar
        hotbar.add(0, player.getHeldItemOffhand());
        // Moves the item in the main hand to the beginning of the list so it is
        // processed first
        hotbar.remove(player.getHeldItemMainhand());
        hotbar.add(0, player.getHeldItemMainhand());
        return hotbar;
    }

    /**
     * Tests whether the specified player has any of the specified item in their
     * entire inventory, including armour slots and offhand.
     */
    public static boolean doesPlayerHaveItem(EntityPlayer player, Item item) {

        for (ItemStack stack : player.inventory.mainInventory) {
            if (stack.getItem() == item) {
                return true;
            }
        }

        for (ItemStack stack : player.inventory.armorInventory) {
            if (stack.getItem() == item) {
                return true;
            }
        }

        for (ItemStack stack : player.inventory.offHandInventory) {
            if (stack.getItem() == item) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the given player is opped on the given server. If the server is a
     * singleplayer or LAN server, this means they have cheats enabled.
     */
    public static boolean isPlayerOp(EntityPlayer player, MinecraftServer server) {
        return server.getPlayerList()
                .getOppedPlayers()
                .getEntry(player.getGameProfile()) != null;
    }

    /**
     * Returns true if the given entity is an EntityLivingBase and not an armour
     * stand; makes the code a bit neater. This was added because armour stands are
     * a subclass of EntityLivingBase, but shouldn't necessarily be treated as
     * living entities - this depends on the situation. <i>The given entity can
     * safely be cast to EntityLivingBase if this method returns true.</i>
     */
    // In my opinion, it's a bad design choice to have armour stands extend
    // EntityLivingBase directly - it would be
    // better to make a instance class which is extended by both armour stands and
    // EntityLivingBase and contains only
    // the code required by both.
    public static boolean isLiving(Entity entity) {
        return entity instanceof EntityLivingBase && !(entity instanceof EntityArmorStand);
    }

    /**
     * Helper method which does a rayTrace for entities from an entity's eye level
     * in the direction they are looking with a specified range, using the tracePath
     * method. Tidies up the code a bit. Border size defaults to 1.
     *
     * @param world
     * @param entity
     * @param range
     * @return
     */
    @Nullable
    public static RayTraceResult standardEntityRayTrace(World world,
                                                        EntityLivingBase entity,
                                                        double range) {
        double dx = entity.getLookVec().x * range;
        double dy = entity.getLookVec().y * range;
        double dz = entity.getLookVec().z * range;
        HashSet<Entity> hashset = new HashSet<Entity>(1);
        hashset.add(entity);
        return WizardryUtilities.tracePath(world, (float) entity.posX, (float) (entity.getBoundingBox().minY + entity
                .getEyeHeight()), (float) entity.posZ, (float) (entity.posX + dx), (float) (entity.posY + entity
                .getEyeHeight() + dy), (float) (entity.posZ + dz), 1.0f, hashset, false);
    }

    /**
     * Helper method which does a rayTrace for entities from a entity's eye level in
     * the direction they are looking with a specified range and radius, using the
     * tracePath method. Tidies up the code a bit.
     *
     * @param world
     * @param entity
     * @param range
     * @param borderSize
     * @return
     */
    @Nullable
    public static RayTraceResult standardEntityRayTrace(World world,
                                                        EntityLivingBase entity,
                                                        double range, float borderSize) {
        double dx = entity.getLookVec().x * range;
        double dy = entity.getLookVec().y * range;
        double dz = entity.getLookVec().z * range;
        HashSet<Entity> hashset = new HashSet<Entity>(1);
        hashset.add(entity);
        return WizardryUtilities.tracePath(world, (float) entity.posX, (float) (entity.getBoundingBox().minY + entity
                .getEyeHeight()), (float) entity.posZ, (float) (entity.posX + dx), (float) (entity.posY + entity
                .getEyeHeight() + dy), (float) (entity.posZ + dz), borderSize, hashset, false);
    }

    /**
     * Method for ray tracing entities (the useless default method doesn't work,
     * despite EnumHitType having an ENTITY field...) You can also use this for
     * seeking.
     *
     * @param world
     * @param x          startX
     * @param y          startY
     * @param z          startZ
     * @param tx         endX
     * @param ty         endY
     * @param tz         endZ
     * @param borderSize extra area to examine around line for entities
     * @param excluded   any excluded entities (the player, etc)
     * @return a RayTraceResult of either the block hit (no entity hit), the entity
     * hit (hit an entity), or null for nothing hit
     */
    @Nullable
    public static RayTraceResult tracePath(World world, float x, float y, float z,
                                           float tx, float ty, float tz, float borderSize,
                                           HashSet<Entity> excluded,
                                           boolean collideablesOnly) {

        Vec3d startVec = new Vec3d(x, y, z);
        // Vec3d lookVec = new Vec3d(tx-x, ty-y, tz-z);
        Vec3d endVec = new Vec3d(tx, ty, tz);
        float minX = x < tx ? x : tx;
        float minY = y < ty ? y : ty;
        float minZ = z < tz ? z : tz;
        float maxX = x > tx ? x : tx;
        float maxY = y > ty ? y : ty;
        float maxZ = z > tz ? z : tz;
        AxisAlignedBB bb = new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ).grow(borderSize, borderSize, borderSize);
        List<Entity> allEntities = world.getEntitiesWithinAABBExcludingEntity(null, bb);
        RayTraceResult blockHit = world.rayTraceBlocks(startVec, endVec);
        startVec = new Vec3d(x, y, z);
        endVec = new Vec3d(tx, ty, tz);
        float maxDistance = (float) endVec.distanceTo(startVec);
        if (blockHit != null) {
            maxDistance = (float) blockHit.hitVec.distanceTo(startVec);
        }
        Entity closestHitEntity = null;
        float closestHit = maxDistance;
        float currentHit = 0.f;
        AxisAlignedBB entityBb;// = ent.getBoundingBox();
        RayTraceResult intercept;
        for (Entity ent : allEntities) {
            if ((ent.canBeCollidedWith() || !collideablesOnly) && ((excluded != null && !excluded
                    .contains(ent)) || excluded == null)) {
                float entBorder = ent.getCollisionBorderSize();
                entityBb = ent.getBoundingBox();
                if (entityBb != null) {
                    entityBb = entityBb.grow(entBorder, entBorder, entBorder);
                    intercept = entityBb.calculateIntercept(startVec, endVec);
                    if (intercept != null) {
                        currentHit = (float) intercept.hitVec.distanceTo(startVec);
                        if (currentHit < closestHit || currentHit == 0) {
                            closestHit = currentHit;
                            closestHitEntity = ent;
                        }
                    }
                }
            }
        }
        if (closestHitEntity != null) {
            blockHit = new RayTraceResult(closestHitEntity);
        }
        return blockHit;
    }

}
