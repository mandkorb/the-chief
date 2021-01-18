package application;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Timer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class SampleController {
	
	@FXML private Button addPotatoButton;
	
	@FXML private Button addTomatoButton;
	
	@FXML private Button addCarrotButton;
	
	@FXML private Button addLettuceButton;

    @FXML private Button addCucumberButton;

    @FXML private Button addZucchiniButton;

    @FXML private Button addPepperButton;

    @FXML private Button addParsleyButton;

    @FXML private Button addRadishButton;

    @FXML private Button addOnionButton;
    
    @FXML private Button addArugulaButton;

    @FXML private Button addBasilButton;
    
    @FXML private Button addOilButton;

    @FXML private Button addCreamButton;

    @FXML private Button addYogurtButton;
    
    @FXML private Button summerSaladButton;

    @FXML private Button winterSaladButton;
    
    @FXML private Button infoButton;

    @FXML private ListView<String> listView;
    
    @FXML private ListView<String> listViewSorted;
    
    @FXML private Button deleteIngradientButton;
    
    @FXML private Button deleteAllIngradientsButton;
    
    @FXML private Label showWeight;

    @FXML private Label showKcal;
     
    @FXML private TextField maxForWeightField;

    @FXML private TextField minForWeightField;
    
    @FXML private TextField maxForKcalField;

    @FXML private TextField minForKcalField;
    
    @FXML private Button reloadWeightRangeButton;
    
    @FXML private Button reloadKcalRangeButton;
    
    @FXML private Button showSortByWeightRange;
    
    @FXML private Button showSortByKcalRange;
    
    @FXML private Button sortByKcalButton;
    
    @FXML private Button sortByWeightButton;
    
    @FXML private Button sortByCategoryButton;
    
    @FXML private Button exportToFile;
    
	private int totalKcal = 0;
	private int totalWeight = 0;	
	private double minForWeight;
    private double maxForWeight;
    private double minForKcal;
    private double maxForKcal;
    String defaultCounterViewStyle = "-fx-text-fill: #2E3348; -fx-border-color: #2E3348;  "
    		+ "-fx-background-color: #fafafa; -fx-border-radius: 5;";
    String redCounterViewStyle = "-fx-border-color: red; -fx-text-fill: red; "
    		+ "-fx-background-color: #fafafa; -fx-border-radius: 5;";
    String greenCounterViewStyle = "-fx-text-fill: green; -fx-border-color: #2E3348; "
			+ "-fx-background-color: #fafafa; -fx-border-radius: 5;";
    String orangeCounterViewStyle = "-fx-text-fill: #dd641d; -fx-border-color: #2E3348; "
			+ "-fx-background-color: #fafafa; -fx-border-radius: 5;";
    
    private boolean getInputForWeight() {
    	boolean check = true;
    	
    	try {
    	minForWeight = Double.parseDouble(minForWeightField.getText());
    	minForWeightField.setStyle(defaultCounterViewStyle);
    	} catch(Exception e) {
    		minForWeightField.setStyle(redCounterViewStyle);
    		check = false;
    	}
    	
    	try {
    		maxForWeight = Double.parseDouble(maxForWeightField.getText());
        	maxForWeightField.setStyle(defaultCounterViewStyle);
        	} catch(Exception e) {
        		maxForWeightField.setStyle(redCounterViewStyle);
        		check = false;
        	}

    	if(maxForWeight < minForWeight) {
    		maxForWeightField.setStyle(redCounterViewStyle);
    		check = false;
    	}
    	
    	return check;
    }
    
    private boolean getInputForKcal() {
    	boolean check = true;
    	   	
    	try {
    	minForKcal = Double.parseDouble(minForKcalField.getText());
    	minForKcalField.setStyle(defaultCounterViewStyle);
    	} catch(Exception e) {
    		minForKcalField.setStyle(redCounterViewStyle);
    		check = false;
    	}
    	
    	try {
    		maxForKcal = Double.parseDouble(maxForKcalField.getText());
    		maxForKcalField.setStyle(defaultCounterViewStyle);
        	} catch(Exception e) {
        		maxForKcalField.setStyle(redCounterViewStyle);
        		check = false;
        	}
    	
    	if(maxForKcal < minForKcal) {
    		maxForKcalField.setStyle(redCounterViewStyle);
    		check = false;
    	}
    	
    	return check;
    }

	private void checkWeight() {
		if(this.totalWeight == 0) {
			showWeight.setStyle(defaultCounterViewStyle);
		}
		else if(this.totalWeight > 0 && this.totalWeight < 200) {
			showWeight.setStyle(orangeCounterViewStyle);
		} else if(this.totalWeight >= 200 && this.totalWeight <= 280) {
			showWeight.setStyle(greenCounterViewStyle);
		} else if(this.totalWeight > 280) {
			showWeight.setStyle(redCounterViewStyle);
		}
	}
	
	private void checkKcal() {		
		if(this.totalKcal == 0) {
			showKcal.setStyle(defaultCounterViewStyle);
		}
		else if(this.totalKcal > 0 && this.totalKcal < 90) {
			showKcal.setStyle(orangeCounterViewStyle);
		} else if(this.totalKcal >= 90 && this.totalKcal <= 225) {
			showKcal.setStyle(greenCounterViewStyle);
		} else if(this.totalKcal > 225) {
			showKcal.setStyle(redCounterViewStyle);
		}
	}
	
	private void printIngradientToListView(Salad ingradient) {
		
		listView.getItems().add("  " + ingradient.getName() + "\t\t  " + ingradient.getCategory() + 
				"\t\t     " + ingradient.getCalories() + "\t\t\t   " + ingradient.getWeight() + " „.");
	}
	
	private void printKcalSort(Salad ingradient) {		
		listViewSorted.getItems().add(ingradient.getName() + " |  ¿ÀŒ–≤Ø: " + ingradient.getCalories());		
	}
	
	private void printWeightSort(Salad ingradient) {		
		listViewSorted.getItems().add(ingradient.getName() + " | ¬¿√¿: " + ingradient.getWeight());		
	}
	
	Potato potato = new Potato(" ¿–“ŒœÀﬂ", "¡¿«Œ¬»…", 77, 90);
	Tomato tomato = new Tomato("œŒÃ≤ƒŒ–", "¡¿«Œ¬»…", 9, 50);
	Carrot carrot = new Carrot("ÃŒ– ¬¿", "¡¿«Œ¬»…", 28, 50);
	Lettuce lettuce = new Lettuce("À»—“ﬂ —¿À¿“”", "ƒŒƒ¿“Œ ", 5, 30);
	Cucumber cucumber = new Cucumber("Œ√≤–Œ ", "¡¿«Œ¬»…", 7, 50);
	Zucchini zucchini = new Zucchini(" ¿¡¿◊Œ ", "¡¿«Œ¬»…", 13, 80);
	Pepper pepper = new Pepper("œ≈–≈÷‹", "¡¿«Œ¬»…", 22, 60);
	Parsley parsley = new Parsley("œ≈“–”ÿ ¿", "ƒŒƒ¿“Œ ", 4, 15);
	Radish radish = new Radish("–≈ƒ»— ¿", "¡¿«Œ¬»…", 16, 40);
	Arugula arugula = new Arugula("–” ŒÀ¿", "ƒŒƒ¿“Œ ", 6, 20);
	Onion onion = new Onion("÷»¡”Àﬂ", "ƒŒƒ¿“Œ ", 4, 10);
	Basil basil = new Basil("–” ŒÀ¿", "ƒŒƒ¿“Œ ", 6, 20);
	Oil oil = new Oil("ŒÀ»¬ Œ¬¿ ŒÀ≤ﬂ", "«¿œ–¿¬ ¿", 155, 30);
	Yogurt yogurt = new Yogurt("…Œ√”–“", "«¿œ–¿¬ ¿", 45, 30);
	Cream cream = new Cream("—Ã≈“¿Õ¿", "«¿œ–¿¬ ¿", 85, 30);
	
    @FXML
    void initialize() {    	
    	showWeight.setText("0");		
    	showKcal.setText("0");
    	ObservableList<Salad> saladList = FXCollections.observableArrayList();
    	ObservableList<Salad> sortByWeightList = FXCollections.observableArrayList();
    	ObservableList<Salad> sortByKcalList = FXCollections.observableArrayList();


    	deleteIngradientButton.setTooltip(new Tooltip("¬Ë‰‡ÎÂÌÌˇ ≥Ì„‡‰≥∫ÌÚ‡"));
    	deleteAllIngradientsButton.setTooltip(new Tooltip("Œ˜ËÒÚËÚË ‚ÒÂ"));
    	sortByCategoryButton.setTooltip(new Tooltip("¬≥‰ÒÓÚÛ‚‡ÚË ÔÓ Í‡ÚÂ„Ó≥ˇÏ"));
    	sortByKcalButton.setTooltip(new Tooltip("¬≥‰ÒÓÚÛ‚‡ÚË ÔÓ Í‡ÎÓ≥ˇÏ"));
    	sortByWeightButton.setTooltip(new Tooltip("¬≥‰ÒÓÚÛ‚‡ÚË ÔÓ ‚‡Á≥"));
    	exportToFile.setTooltip(new Tooltip("«·ÂÂ„ÚË Û Ù‡ÈÎ"));
    	reloadWeightRangeButton.setTooltip(new Tooltip("Œ˜ËÒÚËÚË ÔÓÎˇ ‚‚Ó‰Û ‰≥‡Ô‡ÁÓÌÛ"));
    	reloadKcalRangeButton.setTooltip(new Tooltip("Œ˜ËÒÚËÚË ÔÓÎˇ ‚‚Ó‰Û ‰≥‡Ô‡ÁÓÌÛ"));

    	/*
    	 
    	 —Œ–“”¬¿ÕÕﬂ ≈À≈Ã≈Õ“≤¬ —¿À¿“” “¿ ¬»¬≤ƒ ≤Õ√–¿ƒ≤™Õ“≤¬ ” ¬ ¿«¿ÕŒÃ” ƒ≤¿œ¿«ŒÕ≤
    	 
    	 */
    	
    	sortByCategoryButton.setOnAction(event ->{    		
    		
    		if(!saladList.isEmpty()) {
    			listViewSorted.getItems().clear();
    			
    			listViewSorted.getItems().add("≤Ì„‡‰≥∫ÌÚË ‚≥‰ÒÓÚÓ‚‡Ì≥ Û ÔÓˇ‰ÍÛ ÒÔ‡‰‡ÌÌˇ: ");
    			
    			for(Salad ing : saladList) {
    				if(ing.getCategory() == "¡¿«Œ¬»…") {    					
    					listViewSorted.getItems().add(ing.getName() + " |  ‡ÚÂ„Ó≥ˇ: " + ing.getCategory());
        			}
    			}
        				
        		for(Salad ing : saladList) {
            		if(ing.getCategory() == "ƒŒƒ¿“Œ ") {    					
            			listViewSorted.getItems().add(ing.getName() + " |  ‡ÚÂ„Ó≥ˇ: " + ing.getCategory());
            		}		        
            	}
        		
        		for(Salad ing : saladList) {
            		if(ing.getCategory() == "«¿œ–¿¬ ¿") {    					
            			listViewSorted.getItems().add(ing.getName() + " |  ‡ÚÂ„Ó≥ˇ: " + ing.getCategory());
            		}		        
            	}  			
    		}
    	});
    	
    	
    	sortByKcalButton.setOnAction(event ->{	
    		if(!saladList.isEmpty()) {
    			Comparator<Salad> comparator = Comparator.comparingDouble(Salad::getCalories); 
        		comparator = comparator.reversed();
        		
        		FXCollections.sort(saladList, comparator);
        		listViewSorted.getItems().clear();
        		listViewSorted.getItems().add("≤Ì„‡‰≥∫ÌÚË ‚≥‰ÒÓÚÓ‚‡Ì≥ Û ÔÓˇ‰ÍÛ ÒÔ‡‰‡ÌÌˇ: ");
        			
        		for(Salad ing : saladList) {
        			printKcalSort(ing);
            	}
    		}
    	});
    	
    	sortByWeightButton.setOnAction(event ->{
    		if(!saladList.isEmpty()) {
    			Comparator<Salad> comparator = Comparator.comparingDouble(Salad::getWeight); 
        		comparator = comparator.reversed();
        		
        		FXCollections.sort(saladList, comparator);
        		listViewSorted.getItems().clear();        		 
        		listViewSorted.getItems().add("≤Ì„‡‰≥∫ÌÚË ‚≥‰ÒÓÚÓ‚‡Ì≥ Û ÔÓˇ‰ÍÛ ÒÔ‡‰‡ÌÌˇ: ");
        			
        		for(Salad ing : saladList) {
            		printWeightSort(ing);
            	}
        		
    		}
    	});  
    	
    	showSortByWeightRange.setOnAction(event ->{    		
    		
    		if(getInputForWeight() && !saladList.isEmpty()) {
    			listViewSorted.getItems().clear();
    			listViewSorted.getItems().add("Œ‚Ó˜≥, ˘Ó Ô≥‰ıÓ‰ˇÚ¸ Á‡‰‡ÌÓÏÛ ‰≥‡Ô‡ÁÓÌÛ:");
    			for(Salad ing : saladList) {
    				if(ing.getWeight() >= minForWeight && ing.getWeight() <= maxForWeight) {    					
    					sortByWeightList.add(ing);
    					printWeightSort(ing);        		    	        		
    				}
    			}
    			
    			if(sortByWeightList.isEmpty()) {
    				listViewSorted.getItems().add("ÕÂÏ‡∫ ÒÔ≥‚Ô‡‰≥Ì¸. œÂÂ‚≥ÚÂ ‰≥‡Ô‡ÁÓÌ ÁÌ‡˜ÂÌ¸!");
    			}
    		}
    		
    	});
    	
    	showSortByKcalRange.setOnAction(event ->{    		
    		
    		if(getInputForKcal() && !saladList.isEmpty()) {
    			listViewSorted.getItems().clear();
    			listViewSorted.getItems().add("Œ‚Ó˜≥, ˘Ó Ô≥‰ıÓ‰ˇÚ¸ Á‡‰‡ÌÓÏÛ ‰≥‡Ô‡ÁÓÌÛ:");
    			for(Salad ing : saladList) {
    				if(ing.getCalories() >= minForKcal && ing.getCalories() <= maxForKcal) {    					
    					sortByKcalList.add(ing);    					    					
    					printKcalSort(ing);        		
    				}
    			}
    			
    			if(sortByKcalList.isEmpty()) {
    				listViewSorted.getItems().add("ÕÂÏ‡∫ ÒÔ≥‚Ô‡‰≥Ì¸. œÂÂ‚≥ÚÂ ‰≥‡Ô‡ÁÓÌ ÁÌ‡˜ÂÌ¸!");
    			}
    		}
    	});
    	
    	/*
      	 
   	 	«¿œ»— —¿À¿“” ƒŒ ‘¿…À”
   	 
    	*/    	
    	
    	exportToFile.setOnAction(event ->{
    		if(!saladList.isEmpty()) {
    			File f = new File("D:\\Projects\\JavaProjects\\eclipseWorkspace\\The Chief\\SALAD.txt");
        		
        		try {
    				PrintWriter pw = new PrintWriter(f);
    				pw.println("≤Ì„‡‰≥∫ÌÚË ‚‡¯Ó„Ó Ò‡Î‡ÚÛ: ");
    				for(Salad ing : saladList) {
    					pw.println(ing.toString());
    				}
    				
    				listView.getItems().add("\n«‡ÔËÒ ‰Ó Ù‡ÈÎÛ ÔÓÈ¯Ó‚ ÛÒÔ≥¯ÌÓ!");				
    				pw.close();
    				
    			} catch (FileNotFoundException e) {
    				listViewSorted.getItems().clear();
    				listViewSorted.getItems().add("‘‡ÈÎ ÌÂ ÁÌ‡È‰ÂÌÓ");
    			}
    		}
    		
    	});
    	
    	/*
   	 
   	 	–≈¿À≤«¿÷≤ﬂ ƒŒƒ¿¬¿ÕÕﬂ ≤Õ√–¿ƒ≤™Õ“≤¬-Œ¬Œ◊≈… ƒŒ —¿À¿“”
   	 
    	*/
    	
    	addPotatoButton.setOnAction(event ->{
    		    		
    		if(!saladList.contains(potato)) {
    			saladList.add(potato);
    			printIngradientToListView(potato);
    			
    			totalKcal += potato.getCalories();
        		totalWeight += potato.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}
    		
    	});
    	
    	addTomatoButton.setOnAction(event ->{
    		
    		if(!saladList.contains(tomato)) {
    			saladList.add(tomato);
    			printIngradientToListView(tomato);
    			
    			totalKcal += tomato.getCalories();
        		totalWeight += tomato.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}  		
    	});
    	
    	addCarrotButton.setOnAction(event ->{
    		
    		if(!saladList.contains(carrot)) {
    			saladList.add(carrot);
    			printIngradientToListView(carrot);
    			
    			totalKcal += carrot.getCalories();
        		totalWeight += carrot.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}    		
    	});
       	
    	addLettuceButton.setOnAction(event ->{
    		
    		if(!saladList.contains(lettuce)) {
    			saladList.add(lettuce);
    			listView.getItems().add(lettuce.getName() + "\t  " + lettuce.getCategory() + 
        				"\t\t     " + lettuce.getCalories() + "\t\t\t   " + lettuce.getWeight() + " „.");
    			
    			totalKcal += lettuce.getCalories();
        		totalWeight += lettuce.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}    	
    	});
    	
    	
    	addCucumberButton.setOnAction(event ->{

    		if(!saladList.contains(cucumber)) {
    			saladList.add(cucumber);
    			listView.getItems().add("    " + cucumber.getName() + "\t\t  " + cucumber.getCategory() + 
        				"\t\t     " + cucumber.getCalories() + "\t\t\t   " + cucumber.getWeight() + " „.");
    			
    			totalKcal += cucumber.getCalories();
        		totalWeight += cucumber.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}      		
    	});
    	
    	addZucchiniButton.setOnAction(event ->{
    		
    		if(!saladList.contains(zucchini)) {
    			saladList.add(zucchini);
    			printIngradientToListView(zucchini);
    			
    			totalKcal += zucchini.getCalories();
        		totalWeight += zucchini.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}    	
    	});
    	
    	addPepperButton.setOnAction(event ->{
    		
    		if(!saladList.contains(pepper)) {
    			saladList.add(pepper);
    			listView.getItems().add("    " + pepper.getName() + "\t\t  " + pepper.getCategory() + 
        				"\t\t     " + pepper.getCalories() + "\t\t\t   " + pepper.getWeight() + " „.");
    			
    			totalKcal += pepper.getCalories();
        		totalWeight += pepper.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}     
    	});
    	
    	addParsleyButton.setOnAction(event ->{
    		
    		if(!saladList.contains(parsley)) {
    			saladList.add(parsley);
    			printIngradientToListView(parsley);
    			
    			totalKcal += parsley.getCalories();
        		totalWeight += parsley.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}  
    	});
    	
    	addRadishButton.setOnAction(event ->{
    		
    		if(!saladList.contains(radish)) {
    			saladList.add(radish);
    			printIngradientToListView(radish);
    			
    			totalKcal += radish.getCalories();
        		totalWeight += radish.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}
    	});
    	
    	addOnionButton.setOnAction(event ->{
    		
    		if(!saladList.contains(onion)) {
    			saladList.add(onion);
    			printIngradientToListView(onion);
    			
    			totalKcal += onion.getCalories();
        		totalWeight += onion.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}
    	});
    	
    	addArugulaButton.setOnAction(event ->{

    		if(!saladList.contains(arugula)) {
    			saladList.add(arugula);
    			listView.getItems().add("  " + arugula.getName() + "\t\t\t  " + arugula.getCategory() + 
        				"\t\t     " + arugula.getCalories() + "\t\t\t   " + arugula.getWeight() + " „.");
    			
    			totalKcal += arugula.getCalories();
        		totalWeight += arugula.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		} 
    	});
    	
    	addBasilButton.setOnAction(event ->{

    		if(!saladList.contains(basil)) {
    			saladList.add(basil);
    			listView.getItems().add("  " + basil.getName() + "\t\t\t  " + basil.getCategory() + 
        				"\t\t     " + basil.getCalories() + "\t\t\t   " + basil.getWeight() + " „.");
    			
    			totalKcal += basil.getCalories();
        		totalWeight += basil.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		} 
    	});
    	
    	/*
   	 
   	 	–≈¿À≤«¿÷≤ﬂ ƒŒƒ¿¬¿ÕÕﬂ ≤Õ√–¿ƒ≤™Õ“≤¬-«¿œ–¿¬Œ  ƒŒ —¿À¿“”
   	 
    	*/
    	
    	addOilButton.setOnAction(event ->{
    		
    		if(!saladList.contains(oil) && !saladList.contains(cream) && !saladList.contains(yogurt)) {
    			    			
    			saladList.add(oil);
    			listView.getItems().add(oil.getName() + "\t  " + oil.getCategory() + 
        				"\t\t     " + oil.getCalories() + "\t\t\t   " + oil.getWeight() + " „.");
    			
    			totalKcal += oil.getCalories();
        		totalWeight += oil.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    			
    		} else {
    			;
    		} 
    	});
    	
    	addYogurtButton.setOnAction(event ->{
    		
    		if(!saladList.contains(oil) && !saladList.contains(cream) && !saladList.contains(yogurt)) {
    			saladList.add(yogurt);
    			listView.getItems().add("  " + yogurt.getName() + "\t\t\t  " + yogurt.getCategory() + 
        				"\t\t     " + yogurt.getCalories() + "\t\t\t   " + yogurt.getWeight() + " „.");
    			
    			totalKcal += yogurt.getCalories();
        		totalWeight += yogurt.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}    			
    	});
    	
    	addCreamButton.setOnAction(event ->{
    		
    		if(!saladList.contains(oil) && !saladList.contains(cream) && !saladList.contains(yogurt)) {
    			saladList.add(cream);
    			printIngradientToListView(cream);
    			
    			totalKcal += cream.getCalories();
        		totalWeight += cream.getWeight();
        		
        		checkWeight();
        		checkKcal();
        		
        		showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));
    		} else {
    			;
    		}    			
    	});
    	
    	/*
    	 
    	  œ–»√Œ“Œ¬À≈ÕÕ≤ —¿À¿“»
    	  
    	 */
    	
    	winterSaladButton.setOnAction(event ->{
    		listView.getItems().clear();
    		saladList.clear();
    		totalKcal = 0;
    		totalWeight = 0;
    		
    		saladList.add(potato);
    		saladList.add(carrot);
    		saladList.add(zucchini);
    		saladList.add(parsley);
    		saladList.add(onion);
    		saladList.add(cream);
    		
    		for(Salad ing : saladList) {
    			printIngradientToListView(ing);
        		
        		totalKcal += ing.getCalories();
        		totalWeight += ing.getWeight();    			
    		}
    		
    		checkWeight();
    		checkKcal();
    		
    		showWeight.setText(String.valueOf(totalWeight));
    		showKcal.setText(String.valueOf(totalKcal));
    	});
    	
    	summerSaladButton.setOnAction(event ->{
    		listView.getItems().clear();
    		saladList.clear();
    		totalKcal = 0;
    		totalWeight = 0;
    		
    		saladList.add(tomato);    		
    		saladList.add(cucumber);
    		saladList.add(lettuce);
    		saladList.add(pepper);
    		saladList.add(radish);
    		saladList.add(oil);
    		
    		printIngradientToListView(tomato);
    		printIngradientToListView(radish);
    		listView.getItems().add("    " + cucumber.getName() + "\t\t  " + cucumber.getCategory() + 
    				"\t\t     " + cucumber.getCalories() + "\t\t\t   " + cucumber.getWeight() + " „.");
    		listView.getItems().add("    " + pepper.getName() + "\t\t  " + pepper.getCategory() + 
    				"\t\t     " + pepper.getCalories() + "\t\t\t   " + pepper.getWeight() + " „.");
    		listView.getItems().add(lettuce.getName() + "\t  " + lettuce.getCategory() + 
    				"\t\t     " + lettuce.getCalories() + "\t\t\t   " + lettuce.getWeight() + " „.");
    		
    		listView.getItems().add(oil.getName() + "\t  " + oil.getCategory() + 
    				"\t\t     " + oil.getCalories() + "\t\t\t   " + oil.getWeight() + " „.");
    		
    		for(Salad ing : saladList) {       		
        		totalKcal += ing.getCalories();
        		totalWeight += ing.getWeight();    			
    		}
    		
    		checkWeight();
    		checkKcal();
    		
    		showWeight.setText(String.valueOf(totalWeight));
    		showKcal.setText(String.valueOf(totalKcal));
    	});    	
    	
    	/*
     	 
   	 	Œ◊»Ÿ≈ÕÕﬂ ƒ≤¿œ¿«ŒÕ” ¬¬Œƒ” ƒÀﬂ —Œ–“”¬¿ÕÕﬂ ≤Õ√–¿ƒ≤™Õ“≤¬ —¿À¿“”
   	 
    	*/
    	
    	reloadWeightRangeButton.setOnAction(event ->{
    		minForWeightField.setText("");
    		maxForWeightField.setText("");
    		
    		minForWeightField.setStyle(defaultCounterViewStyle);
    		maxForWeightField.setStyle(defaultCounterViewStyle);
    	});
    	
    	reloadKcalRangeButton.setOnAction(event ->{
    		minForKcalField.setText("");
    		maxForKcalField.setText("");
    		
    		minForKcalField.setStyle(defaultCounterViewStyle);
    		maxForKcalField.setStyle(defaultCounterViewStyle);
    	});
    	
    	/*
      	 
   	 	¬»ƒ¿À≈ÕÕﬂ ≤Õ√–¿ƒ≤™Õ“≤¬ « —¿À¿“”
   	 
    	*/
    	
    	deleteIngradientButton.setOnAction(event ->{
    		final int selectedIdx = listView.getSelectionModel().getSelectedIndex();
    		try {
    			totalKcal -= saladList.get(selectedIdx).getCalories();
    			totalWeight -= saladList.get(selectedIdx).getWeight();
    			
    			checkWeight();
        		checkKcal();
    			
    			showWeight.setText(String.valueOf(totalWeight));
        		showKcal.setText(String.valueOf(totalKcal));  
        		        		
        		
    			saladList.remove(selectedIdx);
    			listView.getItems().remove(selectedIdx);    			
    		} catch (Exception e) {}    		
    	});

    	deleteAllIngradientsButton.setOnAction(event ->{
    		listView.getItems().clear();
    		listViewSorted.getItems().clear();
    		saladList.clear();
    		sortByKcalList.clear();
    		sortByWeightList.clear();
    		
    		totalKcal = 0;
    		totalWeight = 0;
    		
    		showWeight.setStyle(defaultCounterViewStyle);
    		showKcal.setStyle(defaultCounterViewStyle);
    		showWeight.setText(String.valueOf(totalWeight));
    		showKcal.setText(String.valueOf(totalKcal));  
    	});
    	
    	/*
     	 
   	 	ƒŒƒ¿“ Œ¬¿ ≤Õ‘Œ–Ã¿÷≤ﬂ
   	 
    	*/
    	
    	infoButton.setOnAction(event ->{
    		
    		Stage infoWindow = new Stage();
    		infoWindow.initModality(Modality.APPLICATION_MODAL);
    		
			try {
				AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("info.fxml"));
				Scene newScene = new Scene(root, 600, 400);
				infoWindow.setScene(newScene);
				infoWindow.setTitle("Additional information window");
				infoWindow.showAndWait();
			} catch (IOException e) {}

    	});
    }
    
//	ObservableList<Salad> userSaladlList = FXCollections.observableArrayList();
//	createUserTemplateButton.setOnAction(event ->{
//		
//		if(!saladList.isEmpty()) { 
//			if(userSaladlList.isEmpty()) {
//				for(Salad ing : saladList) {
//    				userSaladlList.add(ing);
//    			}
//			}
//			else {
//				userSaladlList.clear();
//				for(Salad ing : saladList) {
//    				userSaladlList.add(ing);
//    			}
//			}
//			
//			listView.getItems().add("\nYou successfully created new salad");
//			
//		} else {    			
//			listView.getItems().add("Falied! Check input data and try again");
//		}
//	});
//	
//	userSaladButton.setOnAction(event ->{
//		listView.getItems().clear();
//		totalKcal = 0;
//		totalWeight = 0;
//		
//		for(Salad ing : userSaladlList) {
//			printIngradientToListView(ing);
//    		
//    		totalKcal += ing.getCalories();
//    		totalWeight += ing.getWeight();    			
//		}
//		
//		checkWeight();
//		checkKcal();
//		
//		showWeight.setText(String.valueOf(totalWeight));
//		showKcal.setText(String.valueOf(totalKcal));
//	});
}
