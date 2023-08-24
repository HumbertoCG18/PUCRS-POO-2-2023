# Exercício Introdutório / Etapa 1: preparação #

### <h2>Instalação</h2>
1. <p>Baixe o exemplo das classes "Placa" e "Veiculo" disponíveis no Moodle.</p>
1. <p>Abra o exemplo no VSCode, estude o código e responda as perguntas que seguem:</p>

## Códigos

```java
// App.java
import java.util.Scanner;

public class App{
    public static void main(String args[]){ 
      Scanner s = new Scanner(System.in);
      System.out.println("Nome do professor?");
      String nome  = s.next();
      System.out.println("Quantos anos de experiencia ele tem?");
      int experiencia  = s.nextInt();
      Professor p = new Professor(nome,experiencia);
      System.out.println(p.toString());
      System.out.println("Categoria:"+p.classifica());
      s.close();
    }
  }
```

```java
// Placa.java
import java.util.regex.Pattern;

public class Placa{
    private String pais;
    private String codigo; // LLLNLNN

    public Placa(String pais,String codigo){
        this.pais = pais;
        if (Pattern.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}", codigo) == true){
            this.codigo = codigo;
        }else{
            this.codigo = "AAA0A00";
        }
    }

    public void setPais(String pais){
        this.pais = pais;
    }

    public String getPais(){
        return pais;
    }

    public String getCodigo(){
        return codigo;
    }

    public String toString(){
        if (codigo.equals("AAA0A00")){
            return("Invalida!");
        }else{
            return(codigo+":"+pais);
        }
    }
}

```

```java
// Veiculo.java

public class Veiculo{
    private final double consumoPorLitro = 10;
    private Placa placa;
    private double combustivel;
    
    public Veiculo(Placa placa){
        this.placa = placa;
        combustivel = 0;
    }

    public void setPais(String pais){
        placa.setPais(pais);
    }

    public Placa getPlaca(){
        return placa;
    }

    public double getCombustivelNoTanque(){
        return combustivel;
    }

    public double abastece(double litros){
        if (litros > 0.0){
            combustivel += litros;
        }
        return combustivel;
    }

    // Simula o deslocamento do carro por uma determinada distancia em Km:
    //  - Diminui a quantidade de combustivel gasto do tanque
    //  - Retorna a distancia efetivamente percorrida (com o combustivel disponivel)
    public double dirige(double distancia){
        // Para distancias impossíveis retorna 0
        if (distancia <= 0){
            return 0.0;
        }
        // Calcula o consumo para a distancia
        double consumo = distancia / consumoPorLitro;
        // Diminui o combustivel gasto e retorna a distancia percorrida
        if (combustivel >= consumo){
            combustivel -= consumo;
            return distancia;
        }else{
            double distPossivel = combustivel * consumoPorLitro;
            combustivel = 0.0;
            return distPossivel;
        }
    }

    public String toString(){
        return "Placa: "+getPlaca()+", combustivel no tanque: "+getCombustivelNoTanque();
    }
}


```

 # Questões #

A) Quais são os atributos (variáveis) da classe "Placa"?

B) Porque os atributos da classe "Placa" são privados?

C) A classe "Placa" tem método construtor? O que ele faz? Quando ele é executado?

D) Toda a classe deve ter um método construtor? O que acontece quando uma classe não tem método construtor?

E) O que são métodos “getter” e “setter”? O que os caracteriza?

F) A classe "Placa" tem métodos do tipo "setter"? Por quê?

G) Quais as implicações em se criar métodos “setter” para todos os atributos de uma classe?

H) Quantos atributos tem a classe "Veiculo"? De que tipo são? Algum dos atributos de "Veiculo" indica um relacionamento entre as classes?

I) Porque os atributos da classe "Veiculo" são privados? Qual a vantagem de se manter os atributos das classes privados?

J) A classe "Veiculo" tem método construtor? O que ele faz? Quando é executado?

K) Porque não é necessário criar uma instancia da classe "Placa" no construtor da classe "Veiculo"?

L) A classe "Veiculo" tem métodos do tipo "setter"? Por quê?

M) Para que serve o modificador "final" no atributo "consumoPorLitro"?

N) De que maneira funcionam os métodos "getCombustivelNoTanque", "abastece" e "dirige"?

O) Quantas instâncias de que classes são criadas no "main"?




<br><h1>Etapa 2: exercícios de programação </h2>

<p> 1. Reescreva o método “main” de maneira que o mesmo receba pelos argumentos da linha de comando a placa do veículo, a quantidade de combustível que o mesmo deve ser abastecido e a distância que ele deve percorrer e imprima: os dados do veículo, a distância efetivamente percorrida (com o combustível disponível) e o combustível remanescente no tanque. Faça consistência sobre a quantidade e o tipo dos parâmetros informados exibindo uma mensagem adequada se for o caso.</p>

<p> 2. Do jeito que foi projetada, a classe “Veiculo” modela um veículo cujo tanque de combustível tem capacidade infinita. Altere a classe “Veiculo” de maneira que o tanque de combustível passe a ter uma capacidade finita.</p>

<p>3. Reescreva o método "main" da seguinte forma: crie um arranjo e armazene no mesmo 5 veículos diferentes. Apresente para o usuário um menu com três opções: "1) Abastecer", "2) dirigir" e "3) Fim".</p>

<p>A opção 1 solicita a placa do veículo a ser abastecido e a quantidade de combustível e, ao final da operação, exibe na tela a quantidade total de combustível no veículo após o abastecimento. A opção 2 solicita a placa do veículo que o usuário pretende dirigir e a distância a ser percorrida e, ao final da operação, imprime na tela a distância efetivamente percorrida e a quantidade de combustível remanescente no tanque. Tanto a opção 1 como a opção 2 devem exibir a mensagem "Veiculo não encontrado" caso a placa informada não conste do cadastro (arranjo). A opção 3 deve imprimir os dados de todos os veículos cadastrados e encerrar o programa.</p>

<p>4. Escreva um novo código para o método "main": crie uma instancia da classe "Placa" e informe a esta mesma placa para duas instancias de "Veiculo". Exiba na tela as informações de cada veículo a partir do método "toString". Em seguida, usando o método "setPais" da classe "Veiculo", altere o país do segundo veículo e imprima.</p>





## Considerações Finais

3) Compile e execute o código.

4) Certifique-se de ter dominado todo o processo de edição, compilação e execução de programas proposto para este semestre.

