Feature: Crear un usuario
  Con el objetivo de agregar clientes al sistema
  Yo como usuario admin necesito poder crear un usuario
  para poder interactuar con el sistema

  Scenario: Creacion exitosa de un usuario en la API
    Given Carlos el administrador necesita agregar clientes al sistema
    When el envia la informacion requerida para la creacion
    Then el debe obtener la informacion de id y fecha actual