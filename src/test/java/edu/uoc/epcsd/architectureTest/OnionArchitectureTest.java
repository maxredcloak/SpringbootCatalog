package edu.uoc.epcsd.architectureTest;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = "edu.uoc.epcsd.showcatalog")
public class OnionArchitectureTest {

    @ArchTest
    static final ArchRule onion_architecture_is_respected = onionArchitecture()
	    .domainModels("..domain..")
	    .domainServices("..domain.service..")
	    .applicationServices("..application..")
	    .adapter("rest", "..application.rest..")
	    .adapter("kafka", "..infrastructure.kafka..")
	    .adapter("persistence", "..repository.jpa..");

}