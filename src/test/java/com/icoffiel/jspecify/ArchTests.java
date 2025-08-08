package com.icoffiel.jspecify;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.jmolecules.archunit.JMoleculesArchitectureRules;

@AnalyzeClasses(packages = "com.icoffiel.jspecify.platform")
public class ArchTests {

    @ArchTest
    ArchRule onionArchitecture = JMoleculesArchitectureRules.ensureOnionClassical();
}
