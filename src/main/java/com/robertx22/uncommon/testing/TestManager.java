package com.robertx22.uncommon.testing;

import com.robertx22.uncommon.testing.tests.CheckAllGearsHaveAffix;

public class TestManager {

    private static boolean RunTests = false;

    public static void RunAllTests() {

        if (RunTests) {

            CheckAllGearsHaveAffix.check();

        }

    }

}
