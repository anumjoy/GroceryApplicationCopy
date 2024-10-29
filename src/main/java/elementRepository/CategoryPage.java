package elementRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtility;
import utilities.WaitUtilities;

public class CategoryPage {
	WebDriver driver;
	GeneralUtility gu=new GeneralUtility();
	WaitUtilities wu=new WaitUtilities();
	
	String editCategoryName;
	String storedEditedCategoryName;
	
	public CategoryPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(xpath="//h1[text()='List Categories']")
	WebElement categoryTitle;
	@FindBy(id="category")
	WebElement editCategoryElement;
	@FindBy(xpath="//button[@class='btn btn-danger']")
	WebElement categoryUpdateButton;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	WebElement updateAlertMsg;
	//Delete
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	WebElement deleteAlertMsg;
	
	
	public String verifyCategoryTitle() {
		return categoryTitle.getText();		
	}
	
	//Edit Category
	public void editCategory(int row,int column) {
		String editpath="//tbody//tr[" + row + "]//td[" + column + "]//i[contains(@class,'fas fa-edit')]";
		WebElement editCategoryButton=driver.findElement(By.xpath(editpath));
		editCategoryButton.click();
		//
		String editCategoryName="Edited"+gu.generateCurrentDateAndTime();
		editCategoryElement.clear();
		editCategoryElement.sendKeys(editCategoryName);
		this.editCategoryName=editCategoryName;
		categoryUpdateButton.click();		
	}
	
	public String getUpdateAlertMsg() {		
		return updateAlertMsg.getText();
	}
	
	// Check and compare the edited category name
    public String getEditedCategory() {    
        return editCategoryName;      
    }	
	public String getEditedCategoryFromTable(int row, int column) {        
        String tablePath = "//table[@class='table table-bordered table-hover table-sm']/tbody/tr[" + row + "]/td[" + column + "]";
        WebElement editedTableValue = driver.findElement(By.xpath(tablePath));
        this.storedEditedCategoryName = editedTableValue.getText();        
        return storedEditedCategoryName;
    }
	//Delete Category
	public void deleteNews(int row, int column) {
		String deletePath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + row + "]//td["+ column + "]//i[@class='fas fa-trash-alt']";
		WebElement deleteElement = driver.findElement(By.xpath(deletePath));
		deleteElement.click();
		gu.acceptAlert(driver);
	}
	public String getDeleteAlert() {
		return deleteAlertMsg.getText();
	}
	
	//


}
