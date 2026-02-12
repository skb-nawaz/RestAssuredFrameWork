package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // =========================================================
    // 1️⃣ READ COMPLETE EXCEL DATA (Create User API Data)
    // =========================================================
    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {

        // Excel file path
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLutilities xl = new XLutilities(path);

        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);

        // rows × columns
        String apidata[][] = new String[rownum][colcount];

        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {

                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);

            }
        }

        return apidata;
    }

    // =========================================================
    // 2️⃣ READ ONLY USERNAMES (Delete User API)
    // =========================================================
    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {

        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLutilities xl = new XLutilities(path);

        int rownum = xl.getRowCount("Sheet1");

        String apidata[] = new String[rownum];

        for (int i = 1; i <= rownum; i++) {

            // column index 1 = username column
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1);

        }

        return apidata;
    }
}
