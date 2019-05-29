package com.robertx22.uncommon.testing;

import com.robertx22.uncommon.testing.tests.CheckAllGearsHaveAffix;

public class TestManager {

    private static boolean RunTests = true;

    public static void RunAllTests() {

        //GenerateCurioDataJsons.tryGenerate();

        if (RunTests) {

            CheckAllGearsHaveAffix.check();

        }

    }

}
