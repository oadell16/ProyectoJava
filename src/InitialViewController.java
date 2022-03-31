import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Locale.Category;
//utils log
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class InitialViewController {
    public static Logger logger = Logger.getLogger("Example");
    public static Locale localFormat = Locale.getDefault(Category.FORMAT);
    public static Locale localDisplay = Locale.getDefault(Category.DISPLAY);
    public static ResourceBundle mainTexts = ResourceBundle.getBundle("texts/generic", localDisplay);
    public static ResourceBundle productTexts = ResourceBundle.getBundle("texts/product", localDisplay);
    public static ResourceBundle providerTexts = ResourceBundle.getBundle("texts/provider", localDisplay);
    public static ResourceBundle customerTexts = ResourceBundle.getBundle("texts/customer", localDisplay);
    public static ResourceBundle presenceTexts = ResourceBundle.getBundle("texts/presence", localDisplay);

    public static void main(String[] args) throws IOException{
        
        Scanner scanner = new Scanner(System.in);
        ProductDAO products = new ProductDAO();
        CustomerDAO customers = new CustomerDAO();
        ProviderDAO providers = new ProviderDAO();
        RegisterPresenceDAO presence = new RegisterPresenceDAO();
        char option;

        try {
            FileHandler fileHandler = new FileHandler("./logs/errors.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            logger.info("Logger set up\n");
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
        
        products.load();
        do {
            mainMenu();
            option = scanner.next().charAt(0);
            optMainMenu(option, scanner,products,customers,providers, presence);
        } while(option!='0');
        products.save();
    }
    /*Menus principales */
    private static void mainMenu() {
        

        System.out.println(mainTexts.getString("main"));
        System.out.println(mainTexts.getString("-"));
        System.out.println(mainTexts.getString("0"));
        System.out.println(mainTexts.getString("1"));
        System.out.println(mainTexts.getString("2"));
        System.out.println(mainTexts.getString("3"));
        System.out.println(mainTexts.getString("4"));
    }
    private static void optMainMenu(char option, Scanner scanner,ProductDAO products, CustomerDAO customers,ProviderDAO providers,RegisterPresenceDAO presence){
        char optionSubMenu;
        switch (option) {
            case '0':
                System.out.println(mainTexts.getString("exit"));
                break;
            case '1':
                do {
                    menuProducts();
                    optionSubMenu = scanner.next().charAt(0);
                    options( option,optionSubMenu,scanner,products,customers,providers,presence);
                } while(optionSubMenu!='0');
                break;
            case '2':
                do {
                    menuCustomer();
                    optionSubMenu = scanner.next().charAt(0);
                    options( option,optionSubMenu,scanner,products,customers,providers, presence);
                } while(optionSubMenu!='0');
                break;

            case '3':
                do {
                    menuProvider();
                    optionSubMenu = scanner.next().charAt(0);
                    options( option,optionSubMenu,scanner,products,customers,providers,presence);
                } while(optionSubMenu!='0');
                break;
            case '4':
                do {
                    menuPresenceControl();
                    optionSubMenu = scanner.next().charAt(0);
                    options( option,optionSubMenu,scanner,products,customers,providers,presence);
                } while(optionSubMenu!='0');
                break;
            
            default:
                System.out.println(mainTexts.getString("wrong_option"));
                break;
        }
    }
    private static void options(char option,char optionSubMenu,Scanner scanner,ProductDAO products,CustomerDAO customers,ProviderDAO providers,RegisterPresenceDAO presence){
        switch (option) {
            case '0':
                System.out.println(mainTexts.getString("return_main_menu"));
                break;
            case '1':
                optionsProduct(optionSubMenu,scanner,products);
                break;
            case '2':
                optionsCustomer(optionSubMenu,scanner,customers);
                break;
            case '3':
                optionsProvider(optionSubMenu, scanner, providers);
                break;
            case '4':
                optionsPresenceControl(optionSubMenu, scanner, presence);
                break;
        }
    }
    /*sysouts menus*/
    private static void menuProducts() {
        System.out.println(productTexts.getString("menu_p"));
        System.out.println(mainTexts.getString("-"));
        System.out.println(productTexts.getString("0"));
        System.out.println(productTexts.getString("1"));
        System.out.println(productTexts.getString("2"));
        System.out.println(productTexts.getString("3"));
        System.out.println(productTexts.getString("4"));
        System.out.println(productTexts.getString("5"));
        System.out.println(productTexts.getString("6"));
        System.out.println(productTexts.getString("7"));
        System.out.println(productTexts.getString("8"));
        System.out.println(productTexts.getString("9"));
    }
    private static void menuCustomer() {
        System.out.println(customerTexts.getString("menu_c"));
        System.out.println(mainTexts.getString("-"));
        System.out.println(customerTexts.getString("0"));
        System.out.println(customerTexts.getString("1"));
        System.out.println(customerTexts.getString("2"));
        System.out.println(customerTexts.getString("3"));
        System.out.println(customerTexts.getString("4"));
        System.out.println(customerTexts.getString("5"));
    }
    private static void menuProvider() {
        System.out.println(providerTexts.getString("menu_p"));
        System.out.println(mainTexts.getString("-"));
        System.out.println(providerTexts.getString("0"));
        System.out.println(providerTexts.getString("1"));
        System.out.println(providerTexts.getString("2"));
        System.out.println(providerTexts.getString("3"));
        System.out.println(providerTexts.getString("4"));
        System.out.println(providerTexts.getString("5"));
    }
    private static void menuPresenceControl() {
        System.out.println(presenceTexts.getString("menu_p"));
        System.out.println(mainTexts.getString("-"));
        System.out.println(presenceTexts.getString("0"));
        System.out.println(presenceTexts.getString("1"));
        System.out.println(presenceTexts.getString("2"));
        System.out.println(presenceTexts.getString("3"));
    }
    /* Opciones menus product */
    private static void optionsProduct(char optionSubMenu,Scanner scanner,ProductDAO products){
        switch (optionSubMenu) {
            case '0':
                System.out.println(mainTexts.getString("returning"));
                break;
            case '1':
                addUpdateP(scanner,products,optionSubMenu);
                break;
            case '2':
                searchP(scanner, products);
                break;
            case '3':
                addUpdateP(scanner,products,optionSubMenu);
                break;
            case '4':
                delP(scanner, products);
                break;
            case '5':
                System.out.println(products.getMap());
                break;
            case '6':
                modStock(scanner, products) ;
                break;
            case '7':
                saveOrder(scanner, products) ;
                break;
            case '8':
                showAllProducts(scanner, products);
                break;
            case '9':
                showDiscontinued(scanner, products);
                break;
            default:
                System.out.println(mainTexts.getString("wrong_option"));
                break;
        }
    }
    public static void addUpdateP(Scanner scanner, ProductDAO products,char optionSubMenu){
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (optionSubMenu=='1') {
            scanner.nextLine();
            System.out.println(productTexts.getString("create_p"));
            System.out.println(productTexts.getString("create_1"));
            System.out.println(productTexts.getString("create_2")); 
            char type = scanner.next().charAt(0);

            if (type=='1') {
                System.out.println(productTexts.getString("creation_product"));
                System.out.println(mainTexts.getString("-"));

                System.out.print(productTexts.getString("id_product")); 
                int id = scanner.nextInt();
                if (!products.existObj(id)) {
                    scanner.nextLine();
                    System.out.print(productTexts.getString("name_product"));
                    String name = scanner.nextLine();
                    System.out.print(productTexts.getString("price_product")); 
                    int salePrice = scanner.nextInt();
                    System.out.print(productTexts.getString("stock_product"));
                    int stock = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print(productTexts.getString("initialdate_product"));
                    String initialCatalog = scanner.nextLine();
                    LocalDate initialCatalogDate = LocalDate.parse(initialCatalog, dateFormater);

                    System.out.print(productTexts.getString("enddate_product"));
                    String endCatalog = scanner.nextLine();
                    LocalDate endCatalogDate = LocalDate.parse(endCatalog, dateFormater);

                    Product p = new Product(id, name, salePrice, stock,initialCatalogDate,endCatalogDate);
                    
                    products.add(p);
                    
                    System.out.println("\n"+products.search(id)+"\n");
                } else {
                    System.out.println(mainTexts.getString("x"));
                    System.out.println(productTexts.getString("product_exists"));
                    System.out.println(mainTexts.getString("x"));
                }
                
            }else if(type=='2') {
                TreeSet<Product> listIds = new TreeSet<>(); 
                
                System.out.println(productTexts.getString("creation_pack"));
                System.out.println(mainTexts.getString("-"));

                System.out.print(productTexts.getString("id_pack")); 
                int id = scanner.nextInt();

                if (!products.existObj(id)) {
                    scanner.nextLine();
                    System.out.print(productTexts.getString("name_pack")); 
                    String name = scanner.nextLine();
                    System.out.print(productTexts.getString("price_pack")); 
                    int salePrice = scanner.nextInt();
                    System.out.print(productTexts.getString("stock_pack")); 
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print(productTexts.getString("initialdate_pack")); 
                    String initialCatalog = scanner.nextLine();
                    LocalDate initialCatalogDate = LocalDate.parse(initialCatalog, dateFormater);
                    
                    System.out.print(productTexts.getString("enddate_pack")); 
                    String endCatalog = scanner.nextLine();
                    LocalDate endCatalogDate = LocalDate.parse(endCatalog, dateFormater);

                    System.out.println(productTexts.getString("products_to_pack")); 
                    System.out.println(mainTexts.getString("-")); 

                    int continueAdd=1;

                    do {
                        System.out.print(productTexts.getString("product_to_pack")); 
                        int idP = scanner.nextInt();
                        if (products.existObj(idP)){
                            if(!listIds.contains(products.search(idP))){
                                listIds.add(products.search(idP));
                            } else {
                                System.out.println(mainTexts.getString("x"));
                                System.out.println(productTexts.getString("product_in_pack"));
                                System.out.println(mainTexts.getString("x"));
                            }
                            
                        }else{
                            System.out.println(mainTexts.getString("x"));
                            System.out.println(productTexts.getString("product_not_exists"));
                            System.out.println(mainTexts.getString("x"));
                        }
                        
                        System.out.println(productTexts.getString("continue_add_to_pack"));
                        System.out.println(productTexts.getString("continue_add_to_pack_1"));
                        System.out.println(productTexts.getString("continue_add_to_pack_2"));
                        continueAdd = scanner.nextInt();
                    } while (continueAdd!=0);
                    

                    
                    System.out.print(productTexts.getString("discount_pack")); 
                    int discount = scanner.nextInt();
                    Pack p = new Pack(id, name, salePrice, stock,initialCatalogDate,endCatalogDate,listIds,discount);
        
                    products.add(p);
                    System.out.println("\n"+products.search(id)+"\n");
                }else{
                    System.out.println(mainTexts.getString("x"));
                    System.out.println(productTexts.getString("pack_exists"));
                    System.out.println(mainTexts.getString("x"));
                }
            }else{
                System.out.println(mainTexts.getString("wrong_option"));
            }
        } else {
            System.out.println(productTexts.getString("mod_menu"));
            System.out.println(productTexts.getString("mod_menu_1"));
            System.out.println(productTexts.getString("mod_menu_2"));
            int type = scanner.nextInt();
            scanner.nextLine();

            if (type==1) {
                System.out.print(productTexts.getString("id_product")); 
                int id = scanner.nextInt();

                if (products.existObj(id)==false) {
                    System.out.println(productTexts.getString("product_not_exists"));
                } else {
                    System.out.println("\n"+products.search(id)+"\n");
                    scanner.nextLine();
                    System.out.print(productTexts.getString("name_product"));  
                    String name = scanner.nextLine();
                    System.out.print(productTexts.getString("price_product")); 
                    int preuVenda = scanner.nextInt();
                    System.out.print(productTexts.getString("stock_product")); 
                    int stock = scanner.nextInt();

                    System.out.print(productTexts.getString("initialdate_product")); 
                    String initialCatalog = scanner.nextLine();
                    LocalDate initialCatalogDate = LocalDate.parse(initialCatalog, dateFormater);

                    System.out.print(productTexts.getString("enddate_product")); 
                    String endCatalog = scanner.nextLine();
                    LocalDate endCatalogDate = LocalDate.parse(endCatalog, dateFormater);
                    
                    Product p = new Product(id, name, preuVenda, stock, initialCatalogDate, endCatalogDate);
        
                    products.add(p);
                    System.out.println("\n"+products.search(id)+"\n");
                }
            }else if(type==2) {
                System.out.print(productTexts.getString("id_pack")); 
                int id = scanner.nextInt();

                if (products.existObj(id)==false) {
                    System.out.print(productTexts.getString("pack_not_exists")); 
                } else {
                    TreeSet<Product> listIds = new TreeSet<>(); 
                    System.out.println("\n"+products.search(id)+"\n");
                    scanner.nextLine();
                    System.out.print(productTexts.getString("name_pack")); 
                    String name = scanner.nextLine();
                    System.out.print(productTexts.getString("price_pack")); 
                    int salePrice = scanner.nextInt();
                    System.out.print(productTexts.getString("stock_pack")); 
                    int stock = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print(productTexts.getString("initialdate_pack")); 
                    String initialCatalog = scanner.nextLine();
                    LocalDate initialCatalogDate = LocalDate.parse(initialCatalog, dateFormater);

                    System.out.print(productTexts.getString("enddate_pack")); 
                    String endCatalog = scanner.nextLine();
                    LocalDate endCatalogDate = LocalDate.parse(endCatalog, dateFormater);
                    
                    System.out.println(productTexts.getString("products_to_pack")); 
                    System.out.println(mainTexts.getString("-")); 

                    int continueAdd=1;

                    do {
                        System.out.print(productTexts.getString("product_to_pack")); 
                        int idP = scanner.nextInt();
                        if (products.existObj(idP)){
                            if(!listIds.contains(products.search(idP))){
                                listIds.add(products.search(idP));
                            } else {
                                System.out.println(mainTexts.getString("x"));
                                System.out.println(productTexts.getString("product_in_pack"));
                                System.out.println(mainTexts.getString("x"));
                            }
                        }else{
                            System.out.println(mainTexts.getString("x"));
                            System.out.println(productTexts.getString("product_not_exists"));
                            System.out.println(mainTexts.getString("x"));
                        }
                        System.out.println(productTexts.getString("continue_add_to_pack"));
                        System.out.println(productTexts.getString("continue_add_to_pack_1"));
                        System.out.println(productTexts.getString("continue_add_to_pack_2"));
                        continueAdd = scanner.nextInt();
                    } while (continueAdd!=0);

                    System.out.print(productTexts.getString("discount_pack")); 
                    int discount = scanner.nextInt();
                    Pack pack = new Pack(id, name, salePrice, stock,initialCatalogDate,endCatalogDate,listIds,discount);
                    if (!products.equals(pack)){
                        products.add(pack);
                        System.out.println("\n"+products.search(id)+"\n");
                    } else {
                        System.out.println(mainTexts.getString("x"));
                        System.out.println(productTexts.getString("pack_exists"));
                        System.out.println(mainTexts.getString("x"));
                    }
                    
                }
            }else{
                System.out.println(mainTexts.getString("wrong_option"));
            }
        }
    }
    public static void searchP(Scanner scanner, ProductDAO products){
        System.out.print(productTexts.getString("search")); 
        int id = scanner.nextInt();

        if (products.existObj(id)==false) {
            System.out.println(productTexts.getString("product_not_exists"));
        } else {
           System.out.println("\n"+products.search(id)+"\n"); 
        }
    }
    public static void delP(Scanner scanner, ProductDAO products){
        System.out.print(productTexts.getString("delete")); 
        int id = scanner.nextInt();

        if (products.existObj(id)==false) {
            System.out.println(productTexts.getString("product_not_exists"));
        } else {
            products.delete(id);
        }
    }
    public static void modStock(Scanner scanner, ProductDAO products){
    
        System.out.println(productTexts.getString("mod_stock"));
        System.out.println(mainTexts.getString("-"));
        System.out.println(productTexts.getString("mod_stock_1"));
        System.out.println(productTexts.getString("mod_stock_2")); 
        scanner.nextLine();
        int opt = scanner.nextInt();

        if (opt==1) {
            scanner.nextLine();
            System.out.print(productTexts.getString("id_product"));
            int id = scanner.nextInt();
            if (products.existObj(id)) {
                System.out.println(productTexts.getString("options_manual_stock"));
                System.out.println(mainTexts.getString("-"));
                System.out.println(productTexts.getString("options_manual_stock_1"));
                System.out.println(productTexts.getString("options_manual_stock_2")); 
                char option = scanner.next().charAt(0);
    
                switch (option) {
                    case '0':
                    System.out.println(mainTexts.getString("returning"));
                        break;
                    case '1':
                        System.out.print(productTexts.getString("add_stock"));
                        int addStock = scanner.nextInt();
                        products.search(id).putStock(addStock);
                        break;
                    case '2':
                        System.out.print(productTexts.getString("remove_stock"));
                        int subtractStock = scanner.nextInt();
                        products.search(id).takeStock(subtractStock);
                        break;
                    default:
                        System.out.println(mainTexts.getString("wrong_option"));
                        break;
                }
            }else{
                System.out.println(mainTexts.getString("x"));
                System.out.println(productTexts.getString("product_not_exists"));
                System.out.println(mainTexts.getString("x"));
            }
            
        } else if(opt == 2) {
            try {
                DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("comanda_rebuda.txt")));
                boolean cambios = false;
                while (dis.available()>0){
                    int idF = Integer.parseInt(dis.readUTF());
                    int stockF = dis.readInt();

                    if (products.existObj(idF)) {
                        System.out.println(productTexts.getString("prod_before_mod_stock"));
                        System.out.println(products.search(idF));

                        products.search(idF).putStock(stockF);
                        
                        System.out.println(productTexts.getString("prod_after_mod_stock"));
                        System.out.println(products.search(idF)+"\n");
                        cambios=true;
                    }
                }
                dis.close();

                if (!cambios) {
                    System.out.println(productTexts.getString("not_changes"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            System.out.println(mainTexts.getString("wrong_option"));
        }
    }
    public static void saveOrder(Scanner scanner, ProductDAO products){
        int count = 1;
        char moreOrders = 's';
        ArrayList<int[]> productsOrder = new ArrayList<>();

        do {
            System.out.println(productTexts.getString("product")+count);
            System.out.println(mainTexts.getString("-"));

            System.out.print(productTexts.getString("id_product")); 
            int id = scanner.nextInt();
            System.out.print(productTexts.getString("quantity_of_product")); 
            int cant = scanner.nextInt();

            if (products.existObj(id)) {
                if (cant<=products.search(id).getStock()) {
                    System.out.println(productTexts.getString("enough_stock"));
                    int product[]={id,cant};
                    productsOrder.add(product);

                }else{
                    System.out.println(productTexts.getString("not_enough_stock"));
                }

                //Por defecto la opcion es S
                System.out.println(productTexts.getString("continue_add_to_order"));
                moreOrders = scanner.next().charAt(0);

            } else {
                System.out.println(mainTexts.getString("x"));
                System.out.println(productTexts.getString("product_not_exists"));
                System.out.println(mainTexts.getString("x"));
            }
        } while (moreOrders!='n' && moreOrders != 'N');
            
        try {
            System.out.print(productTexts.getString("name_file"));
            scanner.nextLine();
            String fileName = scanner.nextLine();
            System.out.println(fileName);
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            productsOrder.forEach((p)-> {
                try {
                        String id = String.valueOf(p[0]);
                        dos.writeUTF(id);
                        dos.writeInt(p[1]);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            dos.close();
            System.out.println(productTexts.getString("saving_order"));

        } catch (IOException e) {
           e.printStackTrace();
        }
    }
    public static void showAllProducts(Scanner scanner, ProductDAO products){
        List<Product> productListTemp = products.getMap().values().stream().collect(Collectors.toList());

        System.out.println(productTexts.getString("order_prod_menu"));
        System.out.println(productTexts.getString("order_prod_menu_1"));
        System.out.println(productTexts.getString("order_prod_menu_2"));
        System.out.println(productTexts.getString("order_prod_menu_3"));
        Integer orderOption = scanner.nextInt();
        Comparator<Product> productsOrder = null;
        String orderName = "";

        switch (orderOption) {
            case 1:
                productsOrder = new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return p1.getName().compareTo(p2.getName());
                    }
                };
                orderName = "Nombre";
                break;
            case 2:
                productsOrder = new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return p1.getSalePrice().compareTo(p2.getSalePrice());
                    }
                };
                orderName = "Precio";
                break;
            case 3:
                productsOrder = new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return p1.getStock().compareTo(p2.getStock());
                    }
                };
                orderName = "Stock";
                break;
            default:
                System.out.println(mainTexts.getString("wrong_option"));
                break;
        }

        productListTemp.sort(productsOrder);
        System.out.println(productTexts.getString("ordered_by")+orderName);
        for (Product product : productListTemp) {
            System.out.println(product); 
        }

    }
    public static void showDiscontinued(Scanner scanner, ProductDAO products){
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(productTexts.getString("date_discontinued"));
        scanner.nextLine();
        String dateString = scanner.nextLine();
        if (dateString.equals("")) {
            LocalDate date = LocalDate.now();
            System.out.println(products.showDiscontinued(date));
        } else {
            LocalDate date = LocalDate.parse(dateString, dateFormater);
            System.out.println(products.showDiscontinued(date));
        }
        
    }
    /*Opciones menu cliente */
    private static void optionsCustomer(char optionSubMenu,Scanner scanner,CustomerDAO customers){
        switch (optionSubMenu) {
            case '0':
                System.out.println(mainTexts.getString("returning"));
                break;
            case '1':
                addUpdateC(scanner,customers,optionSubMenu);
                break;
            case '2':
                searchC(scanner, customers);
                break;
            case '3':
                addUpdateC(scanner,customers,optionSubMenu);
                break;
            case '4':
                delC(scanner, customers);
                break;
            case '5':
                System.out.println(customers.getMap());
                break;
            default:
                System.out.println(mainTexts.getString("wrong_option"));
                break;
        }
    }
    public static void addUpdateC(Scanner scanner, CustomerDAO customers,char optionSubMenu){
        if (optionSubMenu=='1') {
            System.out.println(customerTexts.getString("creation_customer")); 
            System.out.println(mainTexts.getString("-"));

            System.out.print(customerTexts.getString("id_customer")); 
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print(customerTexts.getString("dni_customer")); 
            String dni = scanner.nextLine();
            scanner.nextLine();

            System.out.print(customerTexts.getString("name_customer")); 
            String name = scanner.nextLine();
            scanner.nextLine();

            System.out.print(customerTexts.getString("lastnames_customer")); 
            String lastNames = scanner.nextLine();
            scanner.nextLine();

            System.out.print(customerTexts.getString("birthdate_customer")); 
            LocalDate birthDate = LocalDate.parse(scanner.nextLine());
            scanner.nextLine();

            System.out.print(customerTexts.getString("email_customer")); 
            String email = scanner.nextLine();
            scanner.nextLine();

            System.out.print(customerTexts.getString("phone_customer")); 
            String phone = scanner.nextLine();
            scanner.nextLine();

            System.out.print(customerTexts.getString("population_customer")); 
            String population = scanner.nextLine();
            scanner.nextLine();

            System.out.print(customerTexts.getString("province_customer")); 
            String province = scanner.nextLine();
            scanner.nextLine();

            System.out.print(customerTexts.getString("postalcode_customer")); 
            int pc = scanner.nextInt();
            scanner.nextLine();

            System.out.print(customerTexts.getString("domicile_customer")); 
            String domicile = scanner.nextLine();
            scanner.nextLine();

            Address address = new Address( population,  province,  pc,  domicile);
            

            Customer c = new Customer(id,dni,name,lastNames,birthDate,email,phone,address);

            if(customers.existObj(c.getIdPerson())==false){
                customers.add(c);
            }else{
                System.out.println(customerTexts.getString("customer_exists"));
            }
        } else {
            System.out.println(customerTexts.getString("modification_customer")); 
            System.out.println(mainTexts.getString("-"));

            System.out.print(customerTexts.getString("id_customer")); 
            int id = scanner.nextInt();

            if (customers.existObj(id)==false) {
                System.out.println(customerTexts.getString("customer_not_exists"));
            } else {
                System.out.println("\n"+customers.search(id)+"\n");
                scanner.nextLine();

                System.out.print(customerTexts.getString("dni_customer")); 
                String dni = scanner.nextLine();
                scanner.nextLine();

                System.out.print(customerTexts.getString("name_customer")); 
                String name = scanner.nextLine();
                scanner.nextLine();

                System.out.print(customerTexts.getString("lastnames_customer")); 
                String lastNames = scanner.nextLine();
                scanner.nextLine();

                System.out.print(customerTexts.getString("birthdate_customer")); 
                LocalDate birthDate = LocalDate.parse(scanner.nextLine());
                scanner.nextLine();

                System.out.print(customerTexts.getString("email_customer")); 
                String email = scanner.nextLine();
                scanner.nextLine();

                System.out.print(customerTexts.getString("phone_customer")); 
                String phone = scanner.nextLine();
                scanner.nextLine();

                System.out.print(customerTexts.getString("population_customer")); 
                String population = scanner.nextLine();
                scanner.nextLine();

                System.out.print(customerTexts.getString("province_customer")); 
                String province = scanner.nextLine();
                scanner.nextLine();

                System.out.print(customerTexts.getString("postalcode_customer")); 
                int pc = scanner.nextInt();
                scanner.nextLine();

                System.out.print(customerTexts.getString("domicile_customer")); 
                String domicile = scanner.nextLine();
                scanner.nextLine();

                Address address = new Address( population,  province,  pc,  domicile);
                

                Customer c = new Customer(id,dni,name,lastNames,birthDate,email,phone,address);

                customers.add(c);
                System.out.println("\n"+customers.search(id)+"\n");
            }
        }  
    }
    public static void searchC(Scanner scanner, CustomerDAO customers){
        System.out.println(customerTexts.getString("search")); 
        int id = scanner.nextInt();

        if (customers.existObj(id)==false) {
            System.out.println(customerTexts.getString("customer_not_exists"));
        } else {
           System.out.println("\n"+customers.search(id)+"\n"); 
        }
    }
    public static void delC(Scanner scanner, CustomerDAO customers){
        System.out.print(customerTexts.getString("delete")); 
        int id = scanner.nextInt();

        if (customers.existObj(id)==false) {
            System.out.println(customerTexts.getString("customer_not_exists"));
        } else {
            customers.delete(id);
        }
    }
    /*Opciones menu Vendedor */
    private static void optionsProvider(char optionSubMenu,Scanner scanner,ProviderDAO providers){
        switch (optionSubMenu) {
            case '0':
                System.out.println(mainTexts.getString("returning"));
                break;
            case '1':
                addUpdateProv(scanner,providers,optionSubMenu);
                break;
            case '2':
                searchProv(scanner, providers);
                break;
            case '3':
                addUpdateProv(scanner,providers,optionSubMenu);
                break;
            case '4':
                delProv(scanner, providers);
                break;
            case '5':
                System.out.println(providers.getMap());
                break;
            default:
                System.out.println(mainTexts.getString("wrong_option"));
                break;
        }
    }
    public static void addUpdateProv(Scanner scanner, ProviderDAO providers,char optionSubMenu){
        if (optionSubMenu=='1') {
            System.out.print("Id del Vendedor: "); 
            int id = scanner.nextInt();
            scanner.nextLine();
    
            System.out.print("DNI del Vendedor: "); 
            String dni = scanner.nextLine();
            scanner.nextLine();
    
            System.out.print("nombre: "); 
            String name = scanner.nextLine();
            scanner.nextLine();
    
            System.out.print("Apellidos: "); 
            String lastNames = scanner.nextLine();
            scanner.nextLine();
    
            System.out.print("Fecha de nacimiento (2000-12-31): "); 
            LocalDate birthDate = LocalDate.parse(scanner.nextLine());
            scanner.nextLine();
    
            System.out.print("Email: "); 
            String email = scanner.nextLine();
            scanner.nextLine();
    
            System.out.print("telefono: "); 
            String phone = scanner.nextLine();
            scanner.nextLine();
    
            System.out.print("Poblacion: "); 
            String population = scanner.nextLine();
            scanner.nextLine();
    
            System.out.print("Provincia: "); 
            String province = scanner.nextLine();
            scanner.nextLine();
    
            System.out.print("Codigo postal: "); 
            int pc = scanner.nextInt();
            scanner.nextLine();
    
            System.out.print(customerTexts.getString("domicile_customer")); 
            String domicile = scanner.nextLine();
            scanner.nextLine();
    
            Address address = new Address( population,  province,  pc,  domicile);
            
    
            Provider p = new Provider(id,dni,name,lastNames,birthDate,email,phone,address);
    
            if(providers.existObj(p.getIdPerson())==false){
                providers.add(p);
            }else{
                System.out.println("No puedes introducir un Vendedor ya existente");
            }
        }else{
            System.out.print("Id del Vendedor: "); 
            int id = scanner.nextInt();
    
            if (providers.existObj(id)==false) {
                System.out.println("El id de Vendedor introducido no existe");
            } else {
                System.out.println("\n"+providers.search(id)+"\n");
                scanner.nextLine();
    
                System.out.print("DNI del Vendedor: "); 
                String dni = scanner.nextLine();
                scanner.nextLine();
    
                System.out.print("nombre: "); 
                String name = scanner.nextLine();
                scanner.nextLine();
    
                System.out.print("Apellidos: "); 
                String lastNames = scanner.nextLine();
                scanner.nextLine();
    
                System.out.print("Fecha de nacimiento (2000-12-31): "); 
                LocalDate birthDate = LocalDate.parse(scanner.nextLine());
                scanner.nextLine();
    
                System.out.print("Email: "); 
                String email = scanner.nextLine();
                scanner.nextLine();
    
                System.out.print("telefono: "); 
                String phone = scanner.nextLine();
                scanner.nextLine();
    
                System.out.print("Poblacion: "); 
                String population = scanner.nextLine();
                scanner.nextLine();
    
                System.out.print("Provincia: "); 
                String province = scanner.nextLine();
                scanner.nextLine();
    
                System.out.print("Codigo postal: "); 
                int pc = scanner.nextInt();
                scanner.nextLine();
    
                System.out.print("Domicilio: "); 
                String domicile = scanner.nextLine();
                scanner.nextLine();
    
                Address address = new Address( population,  province,  pc,  domicile);
                
    
                Provider p = new Provider(id,dni,name,lastNames,birthDate,email,phone,address);
    
                providers.add(p);
                System.out.println("\n"+providers.search(id)+"\n");
            }
        }
        
    }
    public static void searchProv(Scanner scanner,ProviderDAO providers){
        System.out.println("Id del producto que quieres buscar"); 
        int id = scanner.nextInt();

        if (providers.existObj(id)==false) {
            System.out.println("El id de producto introducido no existe");
        } else {
           System.out.println("\n"+providers.search(id)+"\n"); 
        }
    }
    public static void delProv(Scanner scanner, ProviderDAO providers){
        System.out.println("Id del vendedor que quieres borrar"); 
        int id = scanner.nextInt();

        if (providers.existObj(id)==false) {
            System.out.println("El id de vendedor introducido no existe");
        } else {
            providers.delete(id);
        }
    }
    /*Opciones menu Vendedor */
    public static void optionsPresenceControl(char optionSubMenu, Scanner scanner, RegisterPresenceDAO presence){
        switch (optionSubMenu) {
            case '0':
                System.out.println(mainTexts.getString("returning"));
                break;
            case '1':
                entry(scanner, presence);
                break;
            case '2':
                exit(scanner, presence);
                break;
            case '3':
                consult(scanner, presence);
                break;
            default:
                System.out.println(mainTexts.getString("wrong_option"));
                break;
        }
    }
    public static void entry(Scanner scanner, RegisterPresenceDAO presence) {
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.println("Introduce el id del trabajador: ");
        int workerId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce la fecha (dd/MM/aaaa): ");
        String dateString = scanner.nextLine();

        System.out.println("Introduce la hora de entrada(hh:mm:ss): ");
        String timeString = scanner.nextLine();

        LocalDate date = null;
        LocalTime time = null;
        
        //Comprobar si la fecha se a introducido
        if (dateString.equals("")) {
            date = LocalDate.now();
        } else {
            date = LocalDate.parse(dateString, dateFormater);
        }
        //Comprobar si la hora se a introducido
        if (timeString.equals("")) {
            time = LocalTime.now();
        } else {
            time = LocalTime.parse(timeString);
        }

        Presence p = new Presence(workerId, date, time, null);
        if (!presence.existObj(p)) {
            presence.add(p);
            System.out.println(presence.searchOne(workerId, date));
        }else{
            System.out.println("El objeto no se puede agregar, ya existe");
        }
        
    }
    public static void exit(Scanner scanner, RegisterPresenceDAO presence) {
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.println("Introduce el id del trabajador: ");
        int workerId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce la fecha(dd/MM/aaaa): ");
        String dateString = scanner.nextLine();
        System.out.println("Introduce la hora de salida(hh:mm:ss): ");
        String timeString = scanner.nextLine();

        LocalDate date = null;
        LocalTime time = null;
        
        //Comprobar si la fecha se a introducido
        if (dateString.equals("")) {
            date = LocalDate.now();
        } else {
            date = LocalDate.parse(dateString, dateFormater);
        }
        //Comprobar si la hora se a introducido
        if (timeString.equals("")) {
            time = LocalTime.now();
        } else {
            time = LocalTime.parse(timeString);
        }
        if (presence.existObj(presence.searchOne(workerId, date))) {
            presence.searchOne(workerId, date).setLeavingHour(time);
            System.out.println(presence.searchOne(workerId, date));
        }else{
            System.out.println("El objeto no se puede modificar, no existe");
        }
        
    }
    public static void consult(Scanner scanner, RegisterPresenceDAO presence) {
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.print("Introduce el id del trabajador: ");
        int workerId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce la fecha de la consulta (dd/MM/aaaa) sino se mostraran todos los dias del trabajador");
        String dateString = scanner.nextLine();

        if (dateString.equals("")) {
            ArrayList<Presence> presenceTemp= presence.searchMany(workerId);
            for (int i = 0; i < presenceTemp.size(); i++) {
                System.out.println(presenceTemp.get(i));
            }
        } else {
            LocalDate date = LocalDate.parse(dateString, dateFormater);
            System.out.println(presence.searchOne(workerId, date));
        }
    }

}
