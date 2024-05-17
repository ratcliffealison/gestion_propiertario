# gestion_propiertario

Aplicación para gestionar gastos e ingresos de un propietario

AUTHOR: Alison Ratcliffe (alisonratcliffe.me)
VERSION: 1.0

```
# Aplicación de Gestión de Ingresos y Gastos

Esta aplicación de Java proporciona una interfaz gráfica de usuario (GUI) para gestionar los ingresos y gastos de un usuario.

Permite al usuario registrar sus transacciones financieras y realizar un seguimiento de los ingresos y gastos una propiedad en alquiler.



## Descripción

La aplicación consta de las siguientes características principales:

### Registro de Transacciones

Permite al usuario registrar nuevas transacciones, especificando el tipo (ingreso o gasto), el concepto, la cantidad y la fecha de la transacción.

No permite meter transacciones con campos vacios. Las opciones para seleccionar cambian según el tipo de transacción.

### Visualización de Transacciones

Muestra al usuario una lista de todas las transacciones registradas, con la posibilidad de ordenarlas por fecha o tipo.
Esta visualización se puede imprimir en papel.

### Conexión a Base de Datos

La aplicación se conecta a una base de datos SQL para almacenar y recuperar los datos de las transacciones.

Si la base de datos existe, recupera los registros existentes en sus tablas, y si no exista, la crea.

## Requirimientos

Utiliza consultas SQL para gestionar las operaciones de base de datos.

Para establecer una conexión con una base de datos SQL necesitas :

	1. URL de la base de datos
	2. Nombre de usuario y contraseña

Puedes editar estos parametros en la clase " DatabaseInitialiser "



## Uso de la Aplicación

1. Ejecute la aplicación desde la línea de comandos o desde su IDE favorito.
2. Registre nuevas transacciones utilizando la interfaz gráfica proporcionada.
3. Visualice y gestione las transacciones registradas a través de la interfaz de usuario.
4. Imprime tus registros para tu gestor, o exporta la base de datos desde SQL.

¡Espero que esto te ayude a tener una visión general de la aplicación y sus principales funcionalidades! Si necesitas más información o ayuda, no dudes en preguntar --
hello@alisonratcliffe.me .

```
