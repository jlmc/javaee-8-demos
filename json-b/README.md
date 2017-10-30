# JSON-B 

http://json-b.net/users-guide.html

1. JAXB like API

Json Binding, é uma nova Api adicionada no java EE 8 em que o fundamento da sua existencia é padronizar as operações de MARSHALL e UNMARCHALL entre Java Objects e Json documents, bem à semelhança do JAX-B (XML Binding).

Isto é bastante importante, pois vem resolver um grande problema de portabilidade que existia até agora, pois se alterasse mos o application server o resultado do Json seria diferente com alguma facilidade.

2. Customization APIs
        - @JsonbProperty, 
        - @JsonbNillable
        - runtime Configuration Builder
        - @JsonbTransient
        
        
```
// Create custom configuration
JsonbConfig nillableConfig = new JsonbConfig()
    .withNullValues(true);

// Create Jsonb with custom configuration
Jsonb jsonb = JsonbBuilder.create(nillableConfig);
    
    
```        
        
3. Inicialização customizada

```
public class Person {
    private String name;
    private String profession;

    @JsonbCreator
    public Person(@JsonbProperty("name") String name) {
        this.name = name;
    }
}
```

4. Formatters

@JsonbDateFormat ou @JsonbNumberFormat no fields

```java
public class Person {
    public String name;

    @JsonbDateFormat("dd.MM.yyyy")
    private Date birthDate;

    @JsonbNumberFormat(“#0.00")
    public BigDecimal salary;
}
```

or globally using withDateFormat method of JsonbConfig class:

```
// Create custom configuration
JsonbConfig config = new JsonbConfig()
    .withDateFormat("dd.MM.yyyy", null);

// Create Jsonb with custom configuration
Jsonb jsonb = JsonbBuilder.create(config);

```


# Json-B Adapters

```
public class CustomerAdapter implements JsonbAdapter<Customer, JsonObject> {
      public JsonObject adaptToJson(Customer c) throws Exception {...
      public Customer adaptFromJson(JsonObject adapted) throws Exception { ... 
      
        
}
....


// Create custom configuration
JsonbConfig config = new JsonbConfig()
    .withAdapters(new CustomerAdapter());

// Create Jsonb with custom configuration
Jsonb jsonb = JsonbBuilder.create(config);

// Create customer
Customer c = new Customer();

// Initialization code is skipped

// Serialize
jsonb.toJson(customer);


```


        
        
NOTAS: é necessario ter getters and setters :(        