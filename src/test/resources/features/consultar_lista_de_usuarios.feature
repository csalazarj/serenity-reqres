Feature: Consultar una lista de usuarios
  Con el fin de visualizar los datos
  Yo como usuario quiero poder consular
  Para poder validar el funcionamiento de la consulta de varios usuario por pagina

  Scenario: Consulta de lista usuarios
    Given Ivan requiere validar el funcionamiento de la API de consulta
    When el envia la pagina de la lista a consultar
    Then el debe obtener los datos relacionados a los clientes de la pagina