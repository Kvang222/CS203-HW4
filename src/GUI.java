import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import javax.swing.Timer;

public class GUI {
    JFrame frame;
    JTextField textField;
    JLabel logo = new JLabel("Coffee Heaven",JLabel.CENTER);
    private static List<Customer> cList = new ArrayList<>();//Customer list
    private static List<Coffee> oList = new ArrayList<>();//Order list
    private String t1,t2,t3,t4; //Values for textField customer info
    private String t6, t7; //Values for object attributes
    private double t5;
    private String coffeeType; //Holds the type of coffee for switch
    private int count = 0;
    String text;

    public GUI(){
        frame = new JFrame("Coffee Shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void createAndShowGUI(){
        frame.getContentPane().removeAll();
        frame.getContentPane().revalidate();
        createElements();
        createTextField();

        frame.pack();
        frame.setSize(800,1000);
        frame.setVisible(true);
    }

    public void createTextField(){
        textField = new JTextField(20);
        textField.setEditable(false);
        textField.setOpaque(true);
        textField.setFont(new Font(Font.SERIF,Font.PLAIN,24));
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setBackground(new Color(255,255,255));
        textField.setPreferredSize(new Dimension(800,150));
        frame.getContentPane().add(textField,BorderLayout.SOUTH);
    }

    public void createElements(){
        //Company logo
        logo.setFont(new Font("Serif",Font.BOLD | Font.ITALIC,48));
        frame.getContentPane().add(logo,BorderLayout.NORTH);
        //ImageIcon logo = new ImageIcon(".png");
        //JLabel logoLabel = new JLabel(logo,JLabel.CENTER)

        //Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //Button panel
        JPanel buttonsPanel = new JPanel(new GridLayout(2,2,20,20));
        buttonsPanel.setPreferredSize(new Dimension(600,400));

        //Create buttons
        JButton order = new JButton("Order Coffee");
        JButton view = new JButton("View Orders");
        JButton prepare = new JButton("Prepare");
        JButton receipt = new JButton("Reciept");
        JButton exit = new JButton("Exit");
        exit.setForeground(Color.RED);

        //Add buttons
        buttonsPanel.add(order);
        buttonsPanel.add(view);
        buttonsPanel.add(prepare);
        buttonsPanel.add(receipt);

        //Fonts for all except exit
        for (Component c : buttonsPanel.getComponents()){
            c.setFont(new Font(Font.SERIF,Font.PLAIN,36));
        }

        //Exit panel
        JPanel exitPanel = new JPanel();
        exit.setFont(new Font(Font.SERIF,Font.PLAIN,36));
        exitPanel.add(exit);

        //Panel layouts
        mainPanel.add(buttonsPanel,BorderLayout.CENTER);
        mainPanel.add(exitPanel,BorderLayout.SOUTH);
        frame.getContentPane().add(mainPanel,BorderLayout.CENTER);

        //Actions for buttons
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
            }
        });
        order.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                buttonsPanel.setVisible(false);
                customerInfo();
            }
        });
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                viewOrders();
            }
        });
        prepare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                prepareOrders();
            }
        });
        receipt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                receipt();
            }
        });
        
    }

    public void customerInfo(){
        textField.setEditable(true);
        textField.setText("Are you a regular (R) or a premium (P) customer: ");
        textField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                switch (count) {
                    case 0:
                        t4 = textField.getText().substring(49);
                        text = "Customer name: ";
                        textField.setText(text);
                        count++;
                        break;
                    case 1:
                        t1 = textField.getText().substring(text.length());
                        text = "Customer phone number: ";
                        textField.setText(text);
                        count++;
                        break;
                    case 2:
                        t2 = textField.getText().substring(text.length());
                        text = "Customer address: ";
                        textField.setText(text);
                        count++;
                        break;
                    case 3:
                        t3 = textField.getText().substring(text.length());
                        textField.setEditable(false);
                        textField.setText("You may now order your coffee");
                        orderCoffee();
                        count = 0;
                        break;
                }
            }
        });
    }

    public void orderCoffee(){
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel coffeePanel = new JPanel(new GridLayout(2,2,20,20));

        //Create left panel buttons
        JButton espresso = new JButton("Espresso");
        JButton filteredCoffee = new JButton("Filtered Coffee");
        JButton cappuccino = new JButton("Cappuccino");
        JButton mocha = new JButton("Mocha");
        JButton exit = new JButton("Exit");//Local exit button
        exit.setForeground(Color.RED);

        //Add left panel buttons
        coffeePanel.add(espresso);
        coffeePanel.add(filteredCoffee);
        coffeePanel.add(cappuccino);
        coffeePanel.add(mocha);

        //Set fonts and sizes
        for (Component c : coffeePanel.getComponents()){
            c.setFont(new Font(Font.SERIF,Font.PLAIN,24));
        }
        //Exit button for this panel
        exit.setFont(new Font(Font.SERIF,Font.PLAIN,19));
        JPanel exitPanel = new JPanel();
        exitPanel.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
            }
        });

        //Actions for all types
        espresso.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                coffeeType = "Espresso";
                espressoOrder();
            }
        });
        filteredCoffee.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                coffeeType = "Filtered Coffee";
                filteredCoffeeOrder();
            }
        });
        cappuccino.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                coffeeType = "Cappuccino";
                cappuccinoOrder();
            }
        });
        mocha.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                coffeeType = "Mocha";
                mochaOrder();
            }
        });

        leftPanel.add(coffeePanel, BorderLayout.WEST);
        leftPanel.add(exitPanel,BorderLayout.SOUTH);
        setSplitScreen(leftPanel);
    }
    
    //Method to set the split screen
    public void setSplitScreen(JPanel leftPanel) {
        JPanel mainPanel = new JPanel(new GridLayout(1,2));
        JPanel rightPanel = new JPanel(new BorderLayout());
        ImageIcon gif = new ImageIcon("images/Coffee.gif");
        JLabel gifLabel = new JLabel(gif);
        rightPanel.add(gifLabel,BorderLayout.CENTER);
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(logo,BorderLayout.NORTH);
        frame.getContentPane().add(textField,BorderLayout.SOUTH);
        frame.getContentPane().add(mainPanel,BorderLayout.CENTER);
        frame.getContentPane().revalidate();
    }

    public void espressoOrder(){
        JPanel leftPanel = new JPanel(new BorderLayout());

        JPanel shotsPanel = new JPanel(new BorderLayout());
        JLabel shotsLabel = new JLabel("# Shots",JLabel.CENTER);
        shotsLabel.setFont(new Font("Serif",Font.BOLD,34));
        JSlider shotsSlider = new JSlider(JSlider.VERTICAL,1,4,1);
        shotsSlider.setMajorTickSpacing(1);
        shotsSlider.setPaintTicks(true);
        shotsSlider.setPaintLabels(true);
        shotsPanel.add(shotsSlider,BorderLayout.CENTER);
        shotsPanel.add(shotsLabel,BorderLayout.NORTH);
        t5 = (double) shotsSlider.getValue();

        JPanel milkPanel = new JPanel(new BorderLayout());
        JLabel milkLabel = new JLabel("Milk Type",JLabel.CENTER);
        milkLabel.setFont(new Font("Serif",Font.BOLD,34));
        milkPanel.add(milkLabel,BorderLayout.NORTH);

        JPanel eButtonsPanel = new JPanel(new GridLayout(2,2,20,20));
        JButton whole = new JButton("Whole");
        JButton skim = new JButton("Skim");
        JButton soy = new JButton("Soy");
        JButton almond = new JButton("Almond");
        eButtonsPanel.add(whole);
        eButtonsPanel.add(skim);
        eButtonsPanel.add(soy);
        eButtonsPanel.add(almond);
        for (Component c : eButtonsPanel.getComponents()){
            c.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        }
        milkPanel.add(eButtonsPanel,BorderLayout.CENTER);
        
        JButton exit = new JButton("Exit");
        exit.setFont(new Font(Font.SERIF,Font.PLAIN,19));
        exit.setForeground(Color.RED);
        JPanel exitPanel = new JPanel();
        exitPanel.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
            }
        });
        whole.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "Whole";
                extraIngrMenu();
            }
        });
        skim.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "Skim";
                extraIngrMenu();
            }
        });
        soy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "Soy";
                extraIngrMenu();
            }
        });
        almond.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "Almond";
                extraIngrMenu();
            }
        });

        JPanel centerPanel = new JPanel(new GridLayout(1,2));
        centerPanel.add(shotsPanel);
        centerPanel.add(milkPanel);
        leftPanel.add(centerPanel,BorderLayout.CENTER);
        leftPanel.add(exitPanel,BorderLayout.SOUTH);
        setSplitScreen(leftPanel);
    }
    public void filteredCoffeeOrder(){
        JPanel leftPanel = new JPanel(new BorderLayout());
        JLabel brewLabel = new JLabel("Brew Type",JLabel.CENTER);
        brewLabel.setFont(new Font("Serif",Font.BOLD,36));

        JPanel bButtonsPanel = new JPanel(new GridLayout(2,2,20,20));
        JButton coldBrew = new JButton("Cold Brew");
        JButton frenchPress = new JButton("French Press");
        JButton pourOver = new JButton("Pour Over");
        JButton chemex = new JButton("Chemex");
        bButtonsPanel.add(coldBrew);
        bButtonsPanel.add(frenchPress);
        bButtonsPanel.add(pourOver);
        bButtonsPanel.add(chemex);
        for (Component c : bButtonsPanel.getComponents()){
            c.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        }

        JButton exit = new JButton("Exit");
        exit.setFont(new Font(Font.SERIF,Font.PLAIN,19));
        exit.setForeground(Color.RED);
        JPanel exitPanel = new JPanel();
        exitPanel.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
            }
        });
        coldBrew.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "Cold Brew";
                extraIngrMenu();
            }
        });
        frenchPress.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "French Press";
                extraIngrMenu();
            }
        });
        pourOver.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "Pour Over";
                extraIngrMenu();
            }
        });
        chemex.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "Chemex";
                extraIngrMenu();
            }
        });

        leftPanel.add(bButtonsPanel,BorderLayout.CENTER);
        leftPanel.add(brewLabel,BorderLayout.NORTH);
        leftPanel.add(exitPanel,BorderLayout.SOUTH);
        setSplitScreen(leftPanel);
    }
    public void cappuccinoOrder(){
        JPanel leftPanel = new JPanel(new BorderLayout());
        JLabel foamLabel = new JLabel("Foam Level",JLabel.CENTER);
        foamLabel.setFont(new Font("Serif",Font.BOLD,35));

        JPanel fButtonsPanel = new JPanel(new GridLayout(2,2,20,20));
        JButton foam1 = new JButton("1");
        JButton foam2 = new JButton("2");
        JButton foam3 = new JButton("3");
        JButton foam4 = new JButton("4");
        fButtonsPanel.add(foam1);
        fButtonsPanel.add(foam2);
        fButtonsPanel.add(foam3);
        fButtonsPanel.add(foam4);
        for (Component c : fButtonsPanel.getComponents()){
            c.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        }
        
        JButton exit = new JButton("Exit");
        exit.setFont(new Font(Font.SERIF,Font.PLAIN,19));
        exit.setForeground(Color.RED);
        JPanel exitPanel = new JPanel();
        exitPanel.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
            }
        });

        foam1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t5 = 1;
                extraIngrMenu();
            }
        });
        foam2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t5 = 2;
                extraIngrMenu();
            }
        });
        foam3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t5 = 3;
                extraIngrMenu();
            }
        });
        foam4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t5 = 4;
                extraIngrMenu();
            }
        });

        leftPanel.add(exitPanel,BorderLayout.SOUTH);
        leftPanel.add(foamLabel,BorderLayout.NORTH);
        leftPanel.add(fButtonsPanel,BorderLayout.CENTER);
        setSplitScreen(leftPanel);
    }
    public void mochaOrder(){
        JPanel leftPanel = new JPanel(new BorderLayout());
        JLabel chocLabel = new JLabel("Chocolate Type",JLabel.CENTER);
        chocLabel.setFont(new Font("Serif",Font.BOLD,36));

        JPanel cButtonsPanel = new JPanel(new GridLayout(2,2,20,20));
        JButton dark = new JButton("Dark Choc.");
        JButton milk = new JButton("Milk Choc.");
        JButton white = new JButton("White Choc.");
        JButton nutella = new JButton("Nutella Choc.");
        cButtonsPanel.add(dark);
        cButtonsPanel.add(milk);
        cButtonsPanel.add(white);
        cButtonsPanel.add(nutella);
        for (Component c : cButtonsPanel.getComponents()){
            c.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        }
        
        JButton exit = new JButton("Exit");
        exit.setFont(new Font(Font.SERIF,Font.PLAIN,19));
        exit.setForeground(Color.RED);
        JPanel exitPanel = new JPanel();
        exitPanel.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
            }
        });
        dark.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "Dark Chocolate";
                extraIngrMenu();
            }
        });
        milk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "Milk Chocolate";
                extraIngrMenu();
            }
        });
        white.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "White Chocolate";
                extraIngrMenu();
            }
        });
        nutella.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t6 = "Nutella";
                extraIngrMenu();
            }
        });

        leftPanel.add(cButtonsPanel,BorderLayout.CENTER);
        leftPanel.add(chocLabel,BorderLayout.NORTH);
        leftPanel.add(exitPanel,BorderLayout.SOUTH);
        setSplitScreen(leftPanel);
    }

    public void extraIngrMenu(){
        JPanel leftPanel = new JPanel(new BorderLayout());
        JLabel ingrLabel = new JLabel("Extra Ingredients",JLabel.CENTER);
        ingrLabel.setFont(new Font("Serif",Font.BOLD,32));

        JPanel ingrPanel = new JPanel(new GridLayout(3,2,15,15));
        JCheckBox cream = new JCheckBox("Cream");
        JCheckBox cinnamon = new JCheckBox("Cinnamon");
        JCheckBox vanilla = new JCheckBox("Vanilla");
        JCheckBox caramel = new JCheckBox("Caramel");
        JCheckBox chocChips = new JCheckBox("Choc. Chips");
        JCheckBox hazelnut = new JCheckBox("Hazelnut Syrup");
        ingrPanel.add(cream);
        ingrPanel.add(cinnamon);
        ingrPanel.add(vanilla);
        ingrPanel.add(caramel);
        ingrPanel.add(chocChips);
        ingrPanel.add(hazelnut);
        for (Component c : ingrPanel.getComponents()){
            c.setFont(new Font(Font.SERIF,Font.PLAIN,25));
        }

        JButton done = new JButton("Done");
        done.setFont(new Font(Font.SERIF,Font.PLAIN,24));
        done.setForeground(new Color(46, 204, 113));
        JPanel finishPanel = new JPanel();
        finishPanel.add(done);
        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //Adds all the selected extraIngr's to one string
                //Also where the correct coffee objects are created 
                StringBuilder extraIngr = new StringBuilder();
                if (cream.isSelected()){
                    extraIngr.append("Cream, ");
                } 
                if (cinnamon.isSelected()){
                    extraIngr.append("Cinnamon, ");
                }
                if (vanilla.isSelected()){
                    extraIngr.append("Vanilla, ");
                }
                if (caramel.isSelected()){
                    extraIngr.append("Caramel, ");
                }
                if (chocChips.isSelected()){
                    extraIngr.append("Chocolate Chips, ");
                }
                if (hazelnut.isSelected()){
                    extraIngr.append("Hazelnut Syrup, ");
                }
                //To remove extra comas and spaces
                if (extraIngr.length() > 0){
                    extraIngr.setLength(extraIngr.length() -2);
                }
                t7 = extraIngr.toString();

                Coffee orderedCoffee;
                switch(coffeeType){
                    case "Espresso":
                        orderedCoffee = new Espresso("Espresso",3.50,"Rich Concentrated Coffee",3,t7,t5,t6);
                        break;
                    case "Filtered Coffee":
                        orderedCoffee = new FilteredCoffee("Filtered Coffee",2.75,"Smooth,Classic Brew", 5, t7, t6);
                        break;
                    case "Cappuccino":
                        orderedCoffee = new Cappuccino("Cappuccino", 4.00, "Creamy Drink with a Foam Layer",120, t7, t5);
                        break;
                    case "Mocha":
                        orderedCoffee = new Mocha("Mocha",4.50,"Chocolate Coffee Blend with Milk",250,t7,t6);
                        break;
                    default:
                        return;
                }
                
                // Create customer first
                Customer newCustomer;
                if (t4.equals("P")){
                    newCustomer = new PremiumCustomer(t1,t2,t3,t4);
                } else {
                    newCustomer = new RegularCustomer(t1, t2, t3, t4);
                }
                
                // Add both to their respective lists
                cList.add(newCustomer);
                oList.add(orderedCoffee);
                
                // Show payment information
                frame.getContentPane().remove(textField);
                JLabel paymentLabel = new JLabel();
                paymentLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
                paymentLabel.setHorizontalAlignment(JLabel.CENTER);
                paymentLabel.setPreferredSize(new Dimension(800, 150));
                paymentLabel.setOpaque(true);
                paymentLabel.setBackground(Color.WHITE);
                paymentLabel.setText("<html><div style='text-align: center; padding: 20px;'>" + 
                    newCustomer.payCoffee() + "</div></html>");
                frame.getContentPane().add(paymentLabel, BorderLayout.SOUTH);
                frame.revalidate();
                frame.repaint();
                
                // Use Timer to show payment information for 3 seconds
                Timer timer = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().remove(paymentLabel);
                        textField.setText("Order complete.");
                        frame.getContentPane().add(textField, BorderLayout.SOUTH);
                        frame.revalidate();
                        frame.repaint();
                        createAndShowGUI();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        JButton exit = new JButton("Exit");
        exit.setFont(new Font(Font.SERIF,Font.PLAIN,19));
        exit.setForeground(Color.RED);
        JPanel exitPanel = new JPanel();
        exitPanel.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
            }
        });
        JPanel bottomPanel = new JPanel(new GridLayout(2,1));
        bottomPanel.add(finishPanel);
        bottomPanel.add(exitPanel);
        leftPanel.add(bottomPanel,BorderLayout.SOUTH);
        leftPanel.add(ingrPanel,BorderLayout.CENTER);
        leftPanel.add(ingrLabel,BorderLayout.NORTH);
        setSplitScreen(leftPanel);
    }

    public void receipt() {
        //Create receipts folder if there isn't one
        File directory = new File("receipts");
        if (!directory.exists()) {
            directory.mkdir();
        }

        //Clear the whole frame
        frame.getContentPane().removeAll();
        frame.getContentPane().revalidate();

        //Add the logo
        frame.getContentPane().add(logo, BorderLayout.NORTH);

        //The main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        //The orders panel
        JPanel ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        
        //The scroll pane for orders
        JScrollPane scrollPane = new JScrollPane(ordersPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        //Show orders
        if (oList.isEmpty() || cList.isEmpty()) {
            JLabel noOrders = new JLabel("No orders to generate receipts for!", JLabel.CENTER);
            noOrders.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
            ordersPanel.add(noOrders);
        } else {
            for (int i = 0; i < oList.size() && i < cList.size(); i++) {
                Coffee coffee = oList.get(i);
                Customer customer = cList.get(i);
                
                //where the receipt file is generated
                try {
                    String filename = "receipts/receipt_" + (i + 1) + ".txt";
                    FileWriter writer = new FileWriter(filename);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);

                    //Write and format the receipt content
                    bufferedWriter.write("=== Coffee Heaven ===\n\n");
                    bufferedWriter.write("Order #" + (i + 1) + "\n");
                    bufferedWriter.write("Customer: " + customer.getName() + "\n");
                    bufferedWriter.write("Customer Type: " + (customer instanceof PremiumCustomer ? "Premium" : "Regular") + "\n\n");
                    
                    bufferedWriter.write("=== Order Details ===\n");
                    bufferedWriter.write("Coffee: " + coffee.getName() + "\n");
                    bufferedWriter.write("Description: " + coffee.getDescription() + "\n");
                    String extras = coffee.getExtraIngr();
                    bufferedWriter.write("Extras: " + (extras.isEmpty() ? "None" : extras) + "\n\n");
                    
                    double price = coffee.getPrice();
                    //Apply premium discount if applicable
                    if (customer instanceof PremiumCustomer) {
                        price = price * 0.9; // 10% discount
                    }
                    
                    bufferedWriter.write("=== Price Breakdown ===\n");
                    bufferedWriter.write(String.format("Base Price: $%.2f\n", coffee.getPrice()));
                    if (customer instanceof PremiumCustomer) {
                        bufferedWriter.write("Premium Discount: 10%\n");
                        bufferedWriter.write(String.format("Final Price: $%.2f\n\n", price));
                    } else {
                        bufferedWriter.write(String.format("Final Price: $%.2f\n\n", price));
                    }
                    
                    bufferedWriter.write("Thank you for choosing Coffee Heaven!\n");
                    bufferedWriter.write("We hope to serve you again soon!\n");
                    
                    bufferedWriter.close();
                    
                    //Show receipt panel
                    JPanel receiptPanel = new JPanel();
                    receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
                    receiptPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    
                    JLabel orderLabel = new JLabel("Receipt generated for Order #" + (i + 1));
                    orderLabel.setFont(new Font(Font.SERIF, Font.BOLD, 18));
                    
                    JLabel customerLabel = new JLabel("Customer: " + customer.getName() + 
                        " (" + (customer instanceof PremiumCustomer ? "Premium" : "Regular") + ")");
                    JLabel fileLabel = new JLabel("Saved as: " + filename);
                    fileLabel.setForeground(Color.BLUE);
                    
                    receiptPanel.add(orderLabel);
                    receiptPanel.add(customerLabel);
                    receiptPanel.add(fileLabel);
                    receiptPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                    
                    ordersPanel.add(receiptPanel);
                    ordersPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                    
                } catch (IOException e) {
                    JLabel errorLabel = new JLabel("Error generating receipt for Order #" + (i + 1));
                    errorLabel.setForeground(Color.RED);
                    ordersPanel.add(errorLabel);
                }
            }
        }
        
        //Back button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAndShowGUI();
            }
        });
        
        //Add panels to frame
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void viewOrders() {
        //Clear frame
        frame.getContentPane().removeAll();
        frame.getContentPane().revalidate();

        //Add logo
        frame.getContentPane().add(logo, BorderLayout.NORTH);

        //Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        //Orders panel
        JPanel ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        
        //Scroll pane for orders
        JScrollPane scrollPane = new JScrollPane(ordersPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        //Show orders
        if (oList.isEmpty() || cList.isEmpty()) {
            JLabel noOrders = new JLabel("There are no orders to display!", JLabel.CENTER);
            noOrders.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
            ordersPanel.add(noOrders);
        } else {
            for (int i = 0; i < oList.size() && i < cList.size(); i++) {
                Coffee coffee = oList.get(i);
                Customer customer = cList.get(i);
                
                //Order panel
                JPanel orderPanel = new JPanel();
                orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
                orderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                //Add order info
                JLabel orderLabel = new JLabel("Order #" + (i + 1));
                orderLabel.setFont(new Font(Font.SERIF, Font.BOLD, 18));
                
                JLabel customerLabel = new JLabel("Customer: " + customer.getName() + " " +
                    (customer instanceof PremiumCustomer ? "(Premium)" : "(Regular)"));
                
                JLabel coffeeLabel = new JLabel("Coffee: " + coffee.getName() + 
                    " - $" + String.format("%.2f", coffee.getPrice()));
                
                JLabel statusLabel = new JLabel("Status: " + (coffee.isActive() ? "Active" : "Passive"));
                statusLabel.setForeground(coffee.isActive() ? Color.GREEN : Color.RED);
                
                //Add labels to order panel
                orderPanel.add(orderLabel);
                orderPanel.add(customerLabel);
                orderPanel.add(coffeeLabel);
                orderPanel.add(statusLabel);
                orderPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                
                //Add to orders list
                ordersPanel.add(orderPanel);
                ordersPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        
        //Back button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAndShowGUI();
            }
        });
        
        //Add panels to frame
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void prepareOrders() {
        //Clear the whole frame
        frame.getContentPane().removeAll();
        frame.getContentPane().revalidate();

        //Add logo
        frame.getContentPane().add(logo, BorderLayout.NORTH);

        //the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        //the orders panel
        JPanel ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        
        //Scroll pane for orders
        JScrollPane scrollPane = new JScrollPane(ordersPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // checking for active orders
        boolean hasActiveOrders = false;
        for (Coffee coffee : oList) {
            if (coffee.isActive()) {
                hasActiveOrders = true;
                break;
            }
        }
        
        //Show orders
        if (oList.isEmpty() || !hasActiveOrders) {
            JLabel noOrders = new JLabel("No active orders to prepare", JLabel.CENTER);
            noOrders.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
            ordersPanel.add(noOrders);
        } else {
            for (int i = 0; i < oList.size(); i++) {
                Coffee coffee = oList.get(i);
                if (!coffee.isActive()) continue; // Skip passive orders
                
                Customer customer = cList.get(i);
                
                //the order panel
                JPanel orderPanel = new JPanel();
                orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
                orderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                //Add in the order info
                JLabel orderLabel = new JLabel("Preparing Order #" + (i + 1));
                orderLabel.setFont(new Font(Font.SERIF, Font.BOLD, 18));
                
                JLabel customerLabel = new JLabel("Customer: " + customer.getName() + " " +
                    (customer instanceof PremiumCustomer ? "(Premium)" : "(Regular)"));
                JLabel coffeeLabel = new JLabel("Coffee: " + coffee.getName());
                JLabel detailsLabel = new JLabel("Details: " + coffee.getDescription());
                JLabel priceLabel = new JLabel("Price: $" + String.format("%.2f", coffee.getPrice()));
                
                //Extra ingredients if any
                String extras = coffee.getExtraIngr();
                JLabel extrasLabel = new JLabel("Extras: " + (extras.isEmpty() ? "None" : extras));
                
                // when the coffee is prepared, it is marked as passive
                coffee.prepare();
                JLabel statusLabel = new JLabel("Status: Order prepared and marked as passive");
                statusLabel.setForeground(Color.BLUE);
                
                //Add in all the labels
                orderPanel.add(orderLabel);
                orderPanel.add(customerLabel);
                orderPanel.add(coffeeLabel);
                orderPanel.add(detailsLabel);
                orderPanel.add(priceLabel);
                orderPanel.add(extrasLabel);
                orderPanel.add(statusLabel);
                orderPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                
                //Add them to the panel
                ordersPanel.add(orderPanel);
                ordersPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        
        //Back button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAndShowGUI();
            }
        });
        
        //Add panels to frame
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) throws Exception {
        GUI coffeeGUI = new GUI();
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                coffeeGUI.createAndShowGUI();
            }
        });
    }
}
