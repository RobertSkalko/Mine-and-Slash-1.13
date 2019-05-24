function initializeCoreMod() {
    return {
        'coremodone': {
            'target': {
                'type': 'CLASS',
                'name': 'net.minecraft.server.MinecraftServer'
            },
            'transformer': function(classNode) {
                print("Initializing transformation ", classNode.toString());
                var opcodes = Java.type('org.objectweb.asm.Opcodes')
                var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode')
                var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode')
                var api = Java.type('net.minecraftforge.coremod.api.ASMAPI');
                var methods = classNode.methods;

                for (m in methods) {
                    var method = methods[m];

                    if (method.name === "getWorlds" || method.name === "func_212370_w") {
                        print("MMORPG Found method getWorlds() ", method.toString());
                        var code = method.instructions;
                        var instr = code.toArray();
                        code = new MethodInsnNode(opcodes.INVOKESTATIC, "com/robertx22/CoreMod", "getWorlds", "(Lnet/minecraft/server/MinecraftServer;)", false)
                        break;

                    }
                }
                return classNode;
            }
        }
    }
}