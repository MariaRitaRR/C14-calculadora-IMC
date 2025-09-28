# Calculadora de IMC

Projeto desenvolvido em **Java** utilizando **Maven** para gerenciamento de dependências.  
O programa calcula o **Índice de Massa Corporal (IMC)** a partir do peso e altura do usuário e fornece a classificação de acordo com o resultado.

---

## 📋 Descrição do Projeto
A **Calculadora de IMC** solicita ao usuário os valores de **peso (kg)** e **altura (m)**, realiza o cálculo:

IMC = peso / (altura * altura)


E classifica o resultado em:
- Abaixo do peso
- Peso normal
- Sobrepeso
- Obesidade

Além disso, utiliza **SLF4J** para geração de logs informativos, de aviso e de erro.

---

## ⚙️ Tecnologias Utilizadas
- **Java21** 
- **Maven** (para gerenciamento de dependências e build)
- **SLF4J** (para logging)
- **JUnit 5** (para testes automatizados)
- **GitHub Actions** (paraa CI/CD)

---

## 📦 Estrutura do Projeto

CalculadoraIMC/
├── src/
│   └── main/
│       └── java/
│           └── br/inatel/cdg/
│               └── service/
│                   └── CalculadoraIMC.java
├── target/
│   └── CalculadoraIMC-1.0-SNAPSHOT.jar
├── pom.xml
└── README.md


---

## 🛠️ Configuração do Ambiente
1. Certifique-se de ter o **Java (JDK 17+)** instalado:
   java -version

2. Certifique-se de ter o **Maven** instalado:
    mvn -version

## 📥 Instalação e Dependências
1. **Clone o Repositório**
    git clone https://github.com/MariaRitaRR/C14-calculadora-IMC

2. **Abra a Pasta**
    cd calculadoraIMC

3. **Instale as Dependências**
    mvn clean install

4. **Compile e Empacote o Projeto**
    mvn clean package

5.**Execute o programa**
java -jar target/CalculadoraIMC-1.0-SNAPSHOT.jar




=== Calculadora de IMC ===
Digite o seu peso (kg): 70
Digite a sua altura (m): 1.75

Seu IMC é: 22.857142857142858
Classificação: Peso normal


## Regressão Detectada

**Data:** 02/09/2025
**Erro:** Alteração no limite superior do peso normal de 25 para 24
**Testes que falharam:** 
- testClassificarIMC_LimiteSuperiorPesoNormal()
**Solução:** Reverter a alteração para manter o limite em 25 conforme padrão médico
