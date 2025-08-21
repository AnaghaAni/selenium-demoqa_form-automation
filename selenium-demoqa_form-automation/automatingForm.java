package seleniumDemoMain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class automatingForm {

	public static void main(String[] args) throws InterruptedException {
		// Launch Chrome browser
		WebDriver driver = new ChromeDriver();

		// Navigate to the form URL
		driver.get("https://demoqa.com/automation-practice-form");

		// Maximize the browser window
		driver.manage().window().maximize();

		// Create JavaScript executor 
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Map to store expected form input data for verification 
		Map<String, String> expectedData = new LinkedHashMap<>();

		// Locate the main heading element 
		WebElement mainheading = driver.findElement(By.cssSelector("h1"));
		if (mainheading.isDisplayed())
		    System.out.println("Heading Exist");
		else
		    System.out.println("Heading doesn't Exist");

		// Locate the subheading 
		WebElement subheading = driver.findElement(By.cssSelector("h5"));
		if (subheading.isDisplayed())
		    System.out.println("Sub-Heading Exist");
		else
		    System.out.println("Sub-Heading doesn't Exist");

		// Enter first name
		WebElement firstName = driver.findElement(By.id("firstName"));
		firstName.sendKeys("Priya");

		
		String expectedFirstName = firstName.getAttribute("value");
		Thread.sleep(1000);  

		// Enter last name
		WebElement lastName = driver.findElement(By.id("lastName"));
		lastName.sendKeys("K");
		String expectedLastName = lastName.getAttribute("value");
		Thread.sleep(2000);

		// Combine first and last names
		String name = expectedFirstName + " " + expectedLastName;

		// Store in the key "Student Name"
		expectedData.put("Student Name", name);
		

		// Enter email and store value
		WebElement email = driver.findElement(By.cssSelector("input[placeholder='name@example.com']"));
		email.sendKeys("priya@gmail.com");
		// Store the entered email in the expectedData map 
		expectedData.put("Student Email", email.getAttribute("value"));  // Gets the actual input value
		Thread.sleep(1000);
		
		// Locate the radio button label for "Female" 
		WebElement radioFemale = driver.findElement(By.xpath("//label[@for='gender-radio-2']"));
		js.executeScript("arguments[0].scrollIntoView(true)",radioFemale);
		Thread.sleep(2000);
		radioFemale.click(); // Click the label (this triggers the associated hidden <input>tag radio button)
		// Store the selected gender text into the expectedData map
		expectedData.put("Gender",radioFemale.getText());
		Thread.sleep(1000);
		
		
		// Locate and enter mobile number
		WebElement mobilePhn = driver.findElement(By.cssSelector("input[placeholder='Mobile Number']"));
		mobilePhn.sendKeys("9726593678");
		// Store entered mobile number
		expectedData.put("Mobile", mobilePhn.getAttribute("value"));
		Thread.sleep(1000);
		
		
		// Open date picker
		WebElement dob = driver.findElement(By.cssSelector("#dateOfBirthInput"));
		dob.click();
		Thread.sleep(1000);

		// Select birth month
		WebElement monthElement = driver.findElement(By.cssSelector(".react-datepicker__month-select"));
		monthElement.click();
		Thread.sleep(2000);
		Select selectmonth = new Select(monthElement);
		selectmonth.selectByVisibleText("May");
		Thread.sleep(2000);

		// Select birth year
		WebElement yearElement = driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
		yearElement.click();
		Thread.sleep(2000);
		Select selectyear = new Select(yearElement);
		selectyear.selectByValue("2003");
		Thread.sleep(3000);

		// Select birth day
		WebElement day = driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='3']"));
		day.click();
		Thread.sleep(2000);

		// Format and store Date of Birth
		String dobs = dob.getAttribute("value");
		String parts[] = dobs.split(" ");
		String expectedDob = parts[0] + " " + parts[1] + "," + parts[2];
		System.out.println(expectedDob);
		expectedData.put("Date of Birth", expectedDob);
		Thread.sleep(3000);
		
		//Enter subject in drop down
		WebElement subject = driver.findElement(By.id("subjectsInput"));
		subject.sendKeys("Computer Science");
		Thread.sleep(2000);
		subject.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		// Click on 'Hobbies' checkbox 
		WebElement checkBox = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));
		checkBox.click();
		// Store selected hobby
		expectedData.put("Hobbies", checkBox.getText());
		Thread.sleep(2000);

		// Upload file 
		WebElement uploadFile = driver.findElement(By.cssSelector("#uploadPicture"));
		uploadFile.sendKeys("C:\\Users\\HP\\OneDrive\\Desktop\\AnaghaAnilcap.pdf");
		// Store uploaded file name
		String expectedFile = "AnaghaAnilcap.pdf";
		expectedData.put("Picture", expectedFile);
		Thread.sleep(2000);

		// Enter current address
		WebElement currentAddr = driver.findElement(By.id("currentAddress"));
		currentAddr.sendKeys("Villoonni");
		// Store entered address
		expectedData.put("Address", currentAddr.getAttribute("value"));
		Thread.sleep(2000);
		
		// 		Method 1-  dynamic dropdowns:
		// 			1. Types "Rajasthan" in the State field and presses ENTER to select it.
		// 			2. Types "Jaipur" in the City field and presses ENTER to select it.
		// 		These inputs use dynamic dropdowns, so direct typing and ENTER key is used.
		
		//		WebElement state = driver.findElement(By.xpath("//input[@id ='react-select-3-input']"));
		//		state.sendKeys("Rajasthan");
		//		Thread.sleep(2000);
		//		state.sendKeys(Keys.ENTER);
		//		Thread.sleep(2000);
			
		//		WebElement city = driver.findElement(By.xpath("//input[@id ='react-select-4-input']"));
		//		city.sendKeys("Jaipur");
		//		Thread.sleep(2000);
		//		city.sendKeys(Keys.ENTER);
		//		Thread.sleep(2000);
		
		
		
		//		Method 2
		
		// Locate the state dropdown element 
		WebElement stateList = driver.findElement(By.id("state"));

		// Scroll to the state dropdown
		js.executeScript("arguments[0].scrollIntoView(true)", stateList);

		stateList.click();

		// Find all state options inside the dropdown
		List<WebElement> stateDropDown = driver.findElements(By.xpath("//div[@class=' css-11unzgr']/div"));

		// Print the list of dropdown 
		//System.out.println(stateDropDown);

		String selectedState = "";

		// Loop through each state option
		for (WebElement state : stateDropDown) {
		    selectedState = state.getText();

		    // If the desired state is found, click it
		    if (selectedState.equals("Rajasthan")) {
		        js.executeScript("arguments[0].scrollIntoView(true)", state); 
		        state.click();
		        break;
		    }
		}

		// Print selected state
		//System.out.println(selectedState);
		Thread.sleep(2000);

		// Locate the city dropdown
		WebElement cityList = driver.findElement(By.id("city"));

		// Scroll to city dropdown
		js.executeScript("arguments[0].scrollIntoView(true)", cityList);

		// Click to open the city dropdown
		cityList.click();

		// Find all city options in the dropdown
		List<WebElement> cityDropDown = driver.findElements(By.xpath("//div[@class=' css-11unzgr']/div"));

		// Print the list  
		//System.out.println(cityDropDown);

		String selectedcity = "";

		// Loop through city options
		for (WebElement city : cityDropDown) {
		    selectedcity = city.getText();

		    // If the desired city is found, click it
		    if (selectedcity.equals("Jaipur")) {
		        js.executeScript("arguments[0].scrollIntoView(true)", city); 
		        city.click(); 
		        break;
		    }
		}

		// Combine state and city and store in map 
		String StateAndCity = selectedState + " " + selectedcity;
		expectedData.put("State and City", StateAndCity);

		Thread.sleep(2000);

		//Printing the data in map
		//System.out.println(expectedData);
		
		// Submitting the form and validating the displayed table  with expected input

		// Locate the submit button 
		WebElement submitBtn = driver.findElement(By.cssSelector("#submit"));
		submitBtn.click();
		Thread.sleep(2000);

		// Store all the table rows from the submitted result
		List<WebElement> actualList = driver.findElements(By.xpath("//tbody/tr"));

		// Create a LinkedHashMap to store key-value pairs from the table which is displayed
		Map<String, String> actualData = new LinkedHashMap<>();

		// loop through each row to Retrieve key and value
		for (WebElement row : actualList) {
		    // Retrieve all columns from the row
		    List<WebElement> cells = row.findElements(By.tagName("td"));

		   
		    if (cells.size() >= 2) {
		        String key = cells.get(0).getText();     // First column - key
		        String values = cells.get(1).getText();  // Second column - value
		        actualData.put(key, values);             // Store key and values in map
		    }

		   
		    // String key = row.findElement(By.xpath("td[1]")).getText();
		    // String value = row.findElement(By.xpath("td[2]")).getText();
		    // actualData.put(key, value);
		}

		// Print the full map 
		//System.out.println(actualData);

		// Compare actual and expected data stored 
		for (String key1 : expectedData.keySet()) {
		    String expectedValue = expectedData.get(key1);  // Getting expected value
		    String actualValue = actualData.get(key1);      // Getting  actual value
		    
		    if (expectedValue.equals(actualValue))
		        System.out.println(key1 + " matched");
		    else
		        System.out.println(key1 + " not matched");
		}

		// Wait and close browser
		Thread.sleep(2000);
		driver.quit();		

	}

	
}
