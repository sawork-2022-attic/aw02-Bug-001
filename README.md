# POS in Shell

The demo shows a simple POS system with command line interface. Currently it implements three commands which you can see using the `help` command.

```shell
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.7)
 
shell:>help
AVAILABLE COMMANDS

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        history: Display or save the history of previously run commands
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error.

Pos Command
        a: Add a Product to Cart
        n: New Cart
        p: List Products
```

Everytime a customer come to make a purchase, use `n` to create a new cart and then use `a ${productid} ${amount}` to add a product to the cart.

Please make the POS system robust and fully functional by implementing more commands, for instance, print/empty/modify cart.

Implementing a PosDB with real database is very much welcome. 

Please elaborate your understanding in layered systems via this homework in your README.md.

<hr />

Asciinema record is available here: [https://asciinema.org/a/474061](https://asciinema.org/a/474061)

## Understanding in layered systems

Layered design divides a complicated system into different parts, every of which has its own function. A layer communicates with its neighbors by a limited number of interfaces, which is defined as Java `interface` in this demo.

When we change the implementation of any layer, we don't have to consider implementation of other layers, so complexity is significantly reduced. For instance, when adding commands to poshell, we know that interface `PosService` should be able to add item, del item, etc., so we can simply handle return value from `PosService`, and present it to users. `PosService` contains business logic, which is called by upper layer (various commands), and accesses lower-level data by interface `PosDB`. `PosDB` provides functions to access `Cart` and `Product` object, in which product list could be implemented in memory or real database, without affecting upper layer. We can clearly find three-layer design patterns from analysis above: they are Presentation Layer, Business Layer and Data Access Layer, respectively.

