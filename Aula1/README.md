# Exercício Introdutório

Primeiro exercício ministrado da mat

## Instalação
1) Baixe o exemplo das classes "Placa" e "Veiculo" disponíveis no Moodle.
2) Abra o exemplo no VSCode, estude o código e responda as perguntas que seguem:

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

## Questões

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

## Considerações Finais

3) Compile e execute o código.

4) Certifique-se de ter dominado todo o processo de edição, compilação e execução de programas proposto para este semestre.
