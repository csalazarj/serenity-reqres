Feature: Consultar un solo usuario
  Con el fin de visualizar los datos
  Yo como usuario quiero poder consular
  Para poder validar el funcionamiento de la consulta de un usuario

  Scenario: Consulta de un unico usuario
    Given Paula la desarrolladora requiere validar el funcionamiento de la API de consulta
    When ella envia el ID del usuario a consultar
    Then ella debe obtener los datos relacionados al cliente

  Scenario: Consultar un usuario no encontrado
    Given Paula requiere validar el comportamiendo de la API al fallar
    When ella envia el ID de un usuario que no existe en la base de datos
    Then ella debe obtener el codigo de error relacionado