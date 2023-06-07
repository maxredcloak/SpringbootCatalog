package edu.uoc.epcsd.architectureTest;

import org.springframework.stereotype.Service;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "edu.uoc.epcsd.showcatalog.domain")
public class NamingArchitectureTest {

    @ArchTest
    static ArchRule services_should_be_postfixed =
    		classes().that().resideInAPackage("..service").and().areAnnotatedWith(Service.class).should().haveSimpleNameEndingWith("ServiceImpl");
   

}