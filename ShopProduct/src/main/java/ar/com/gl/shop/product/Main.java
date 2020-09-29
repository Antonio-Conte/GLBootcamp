package ar.com.gl.shop.product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.gl.shop.product.config.SwaggerConfig;


@SpringBootApplication
@EnableEurekaClient
@Import(SwaggerConfig.class)
public class Main{

	//private static long id = 0;


	public static void main(final String[] args ){
		SpringApplication.run(Main.class);
	}
	
}
	
		/*
		List<CRUDService> listaEntidades = new ArrayList<CRUDService>();
		List<Object> listaModelos = new ArrayList<Object>();
		

		listaModelos.add(new Category());
		listaModelos.add(new Product());
		listaModelos.add(new Stock());
		
		int i = 0;
		
		RepositoryImpl repo = RepositoryImpl.getInstance();
		
		StockRepositoryImpl stockRepo = StockRepositoryImpl.getInstance();
		
		listaEntidades.add(new CategoryServiceImpl(repo));
		listaEntidades.add(new ProductServiceImpl(repo));
		listaEntidades.add(new StockServiceImpl(stockRepo));
		
        Scanner scan = new Scanner(System.in);
        int opcion;
        
        do{
        	System.out.println("Escoja una opcion: \n"
        			+ "1: Ingresar Producto.\n"
        			+ "2: Ingresar Categoria.\n"
        			+ "3: Ingresar Stock.\n"
        			+ "4: Salir.");
        	
        	opcion = scan.nextInt();
        	
        	switch(opcion) {
        	case 1 : 
        		listaEntidades.get(1).insert(formProduct());
        		break;
        	case 2 :
        		listaEntidades.get(0).insert(formCategory());	
        		break;
        	case 3 :
        		listaEntidades.get(2).insert(formStock());	
        		break;
        	}
        	              
         }while(opcion != 4);
        
        ArrayList<Product> productos = listaEntidades.get(1).select();
        ArrayList<Stock> stocks = listaEntidades.get(2).select();
        ArrayList<Category> categories = listaEntidades.get(0).select();
         
         for(Product producto : productos ) {
         	
             System.out.println("Nombre: " + producto.getName()  +
           		  			 "--- Precio: " + producto.getPrice() +
           		  			 "--- Descripcion: " + producto.getDescription() +
           		  			 "--- Id: " + producto.getId());
             System.out.println();
        }
        
        for(Category category : categories) {
        	
            System.out.println("Nombre: " + category.getName()  +
          		  			 "--- Descripcion: " + category.getDescription() +
          		  			 "--- Id: " + category.getId());
            System.out.println();
       }
        
        for(Stock stock : stocks) {
        	
            System.out.println(
          		  			 "--- Cantidad: " + stock.getQuantity() +
          		  			 "--- Codigo Loc: " + stock.getLocationCode() +
          		  			 "--- Id: " + stock.getId());
            System.out.println();
       }
        
		//prueba de metodos
		for(CRUDService metodo : listaEntidades) {
			metodo.delete(5L);
			metodo.insert(listaModelos.get(i));
			metodo.select();
			metodo.select(4L);
			metodo.update(listaModelos.get(i));
			i++;
		}
		
        
    }
		public static Product formProduct() {
			
			++id;
			
			Scanner scan = new Scanner(System.in);
			
            System.out.println("Porfavor ingrese un nombre de producto: ");
            String name = scan.nextLine();

            System.out.println("Porfavor ingrese una descripcion del producto: ");
            String description = scan.nextLine();
            
            System.out.println("Porfavor ingrese un precio: ");
            double price = scan.nextDouble();
            
            Product producto = new Product();
            producto.setDescription(description);
            producto.setId(id);
            producto.setName(name);
            producto.setPrice(price);
            producto.setCategory(new Category());
            producto.setStock(new Stock());
            
            return producto;
		}
		
		public static Category formCategory() {
			
			++id;
			
			Scanner scan = new Scanner(System.in);
			
            System.out.println("Porfavor ingrese un nombre de categoria: ");
            String name = scan.nextLine();

            System.out.println("Porfavor ingrese una descripcion de la categoria: ");
            String description = scan.nextLine();
                  
            Category categoria = new Category();
            categoria.setDescription(description);
            categoria.setId(id);
            categoria.setName(name);

            return categoria;
		}
		
		public static Stock formStock() {
			
			++id;
			
			Scanner scan = new Scanner(System.in);
			
            System.out.println("Porfavor ingrese una cantidad de stock: ");
            int cantidad = scan.nextInt();

            System.out.println("Porfavor ingrese un codigo de locacion ");
            String locationCode = scan.next();
                  
            Stock stock = new Stock();
            stock.setQuantity(cantidad);
            stock.setId(id);
            stock.setLocationCode(locationCode);

            return stock;
		}
		
	}*/