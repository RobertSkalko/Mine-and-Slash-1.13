package com.robertx22.uncommon.datasaving.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.loenwind.autosave.Reader;
import info.loenwind.autosave.Writer;
import net.minecraft.nbt.NBTTagCompound;

public class LoadSave {

    //Use this if AutoSave ever stops working and you need to test the mod
    private static final boolean useGSON = true;
    private static final String GSONLOC = "_GSON";

    private static final Gson gson = new GsonBuilder().create();

    public static NBTTagCompound Save(Object obj, NBTTagCompound nbt, String loc) {

        if (nbt == null) {
            nbt = new NBTTagCompound();
        }

        if (useGSON) {
            String json = gson.toJson(obj);
            nbt.putString(loc + GSONLOC, json);

        } else {
            Writer.write(nbt, obj);
        }

        return nbt;

    }

    public static <OBJ extends Object> OBJ Load(Class theclass, OBJ newobj,
                                                NBTTagCompound nbt, String loc) {

        if (nbt == null) {
            return null;
        }

        if (useGSON) {
            String json = nbt.getString(loc + GSONLOC);

            if (json.isEmpty()) {
                return null;
            }

            return (OBJ) gson.fromJson(json, theclass);
        } else {

            if (nbt.contains(loc)) {
                NBTTagCompound thenbt = (NBTTagCompound) nbt.get(loc);
                Reader.read(thenbt, newobj);
            }

        }

        return newobj;
    }

}
