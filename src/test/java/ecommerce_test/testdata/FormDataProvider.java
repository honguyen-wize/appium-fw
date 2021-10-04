package ecommerce_test.testdata;

import org.testng.annotations.DataProvider;

public class FormDataProvider {

    @DataProvider (name = "InputFormData")
    public Object[][] getDataForFillingForm(){
        Object[][] data = new Object[][]{
                {"hello", "Argentina"},
                {"world", "Australia"}
        };

        return data;
    }
}