package test.yasuchenya.xml.validator;

import com.yasuchenya.xml.validator.XMLValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class XMLValidatorTest {
    private static final String FILE_PATH = "./src/main/resources/data_xml/gem.xml";
    private static final String SCHEMA_PATH = "./src/main/resources/data_xml/gem.xsd";
    private static final boolean EXPECTED_VALUE = true;

    @Test
    public void testValidate() {
        boolean actual = XMLValidator.validate(FILE_PATH, SCHEMA_PATH);
        boolean expected = EXPECTED_VALUE;
        Assert.assertEquals(actual, expected);
    }
}