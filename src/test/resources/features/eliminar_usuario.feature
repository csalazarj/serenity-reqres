Feature: Eliminar usuario de la API
  Con el objetivo de limpiar la base de datos
  Yo como administrador requiero eliminar un usuario

  Scenario: Eliminar usuario por ID
    Given Felipe el adminstrador requiere eliminar un usuario
    When el envia el ID del usuario a eliminar
    Then el debe obtener un codido de respuesta acorde