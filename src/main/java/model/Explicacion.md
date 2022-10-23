# Explicacion

Con la interfaz generica `DAO<T, K>` definimos el compotamiento definido por el patron de 
disenio DAO.

Con la clase abstracta generica `DAOConHibernate<T, K>` implementamos el comportamiento definido
por el patron de disenio DAO en particular con el framework Hibernate.

Si usararamos otro framework ORM, solo tendriamos que volver a implementar esta clase con las peculiaridades del framework, por ejemplo, `DAOConOtroFramework<T,K>`.

En particular para `Usuario` 

Con la interfaz `ProductoDAO` amarramos los genericos de `DAO<T,K>` con T=Producto y K=String,
ya que la K representa la llave, en este caso el codigo de doce digitos del producto. 

Con la clase concreta `ProductoDAOHibernate` podemos hacer todas las operaciones CRUD sobre instancias
de la clase `Producto`, inicializando los atributos de 
`DAOConHibernate<T=Producto K=String>` para que sepa que **anotaciones** agarrar y a que 
clase **castear** los registros obtenidos de la BD.
