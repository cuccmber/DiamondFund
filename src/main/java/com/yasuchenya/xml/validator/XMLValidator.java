package com.yasuchenya.xml.validator;


import com.yasuchenya.xml.handler.CrystalErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {
    static Logger logger = LogManager.getLogger();

    public static boolean validate(String filePath, String schemaPath){
        boolean isValid = false;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaPath);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(filePath);
            validator.setErrorHandler(new CrystalErrorHandler());
            validator.validate(source);
            isValid = true;
        } catch (SAXException | IOException e) {
            logger.error(filePath + " is not correct or valid");
        }
        return isValid;
    }
}
