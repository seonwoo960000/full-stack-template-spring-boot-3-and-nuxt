package com.devvy.backend;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packagesOf = BackendApplication.class, importOptions = { ImportOption.DoNotIncludeTests.class })
public class ArchitectureTests {
    private static final String applicationClasses = "..application..";
    private static final String domainClasses = "..domain..";
    private static final String infrastructureClasses = "..infrastructure..";
    private static final String interfacesClasses = "..interfaces..";

    @ArchTest
    static final ArchRule classes_in_domain_package_should_not_access_classes_in_other_packages =
        noClasses().that().resideInAPackage(domainClasses)
                   .should().accessClassesThat().resideInAnyPackage(applicationClasses, infrastructureClasses, interfacesClasses);

    @ArchTest
    static final ArchRule classes_in_application_package_should_not_access_classes_in_infrastructure_or_interfaces_package =
        noClasses().that().resideInAPackage(applicationClasses)
                   .should().accessClassesThat().resideInAnyPackage(infrastructureClasses, interfacesClasses);
}
