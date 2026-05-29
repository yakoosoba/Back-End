import java.util.Scanner;

public class media {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double nota1 = sc.nextDouble();
        double nota2 = sc.nextDouble();
        double media = (nota1 + nota2) / 2;

        if (media >= 7.0) {
            System.out.println("ALUNO APROVADO COM MÉDIA " + media);
        }
        else {
            System.out.println("ALUNO REPROVADO POR MÉDIA " + media);
        }

        sc.close();
    }
}