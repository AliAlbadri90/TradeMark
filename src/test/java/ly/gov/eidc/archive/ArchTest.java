package ly.gov.eidc.archive;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ly.gov.eidc.archive");

        noClasses()
            .that()
            .resideInAnyPackage("ly.gov.eidc.archive.service..")
            .or()
            .resideInAnyPackage("ly.gov.eidc.archive.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ly.gov.eidc.archive.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
