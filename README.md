# Parking Manager – Sistema de Gestão de Estacionamento
### Teste Técnico Backend – GCP Global

Este projeto é uma solução completa de gestão de estacionamento, desenvolvida com foco em clareza, robustez, baixa complexidade e alta extensibilidade, seguindo princípios de Arquitetura Limpa e o Padrão Strategy para tarifação.

### Objetivo do Sistema

O sistema é capaz de:

#### Registrar entrada e saída de veículos.

#### Aplicar regras de cobrança específicas por categoria:

Oficial

Residente

Não-Residente

#### Controlar e persistir estadias.

#### Gerar relatórios mensais para veículos residentes.

#### Permitir expansão futura seguindo o Princípio Aberto/Fechado (OCP).

## 🛠️ Tecnologias Utilizadas
#### Linguagem	Java 17+
#### Framework	Spring Boot 3
#### Persistência	Spring Data JPA, Hibernate, MySQL
#### Build	Maven
#### Web	Spring Web
####  MySQL como banco de dados principal.


## Arquitetura e Princípios

O projeto segue uma arquitetura em camadas inspirada no Domain-Driven Design (DDD), garantindo separação clara entre domínio e infraestrutura.

#### Estrutura de Pastas
````
/Application
   /Service
   /UseCases
/Domain
   /Entity
   /Enums
   /Factory
/Infra
   /Controller
   /Repositories
````

### Princípios Aplicados
#### Princípio	Descrição
#### OCP (Open/Closed Principle)	Novos tipos de veículo ou novas tarifas podem ser adicionadas sem modificar o serviço central.
#### Strategy Pattern	Cada categoria de veículo possui sua própria regra de tarifação.
#### SRP (Single Responsibility)	Cada classe possui apenas uma responsabilidade.
### 🧩 Entidades Principais
#### VehicleDomain

Representa os veículos registrados.
````
Atributos:

id
plate
type (OFICIAL, RESIDENTE, NAO_RESIDENTE)
````
#### VehicleEntity

Registro da persistencia do veículo no estacionamento.

Atributos:
````
id
plate
typeVehicle
entryTime
exitTime
acumulatedTime
peding
````

### 🧠 Strategy Pattern para Tarifação

A tarifação é implementada através da interface ITarifador.

#### Tipo de Veículo	Classe	Regra
OFICIAL	TarifadorOficial	Sempre paga 0
RESIDENTE	TarifadorResidente	Acumula horas para cobrança mensal
NÃO RESIDENTE	TarifadorNaoResidente	Calcula valor total no momento da saída

