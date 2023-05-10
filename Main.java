import java.io.IOException;
import java.util.Scanner;

class WrongStudentName extends Exception {
}

class WrongStudentAge extends Exception {
}

class WrongStudentDate extends Exception {
}

class WrongStudentParse extends Exception {
}

class Main {
  public static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    while (true) {
      try {
        int ex = Integer.parseInt(menu());
        switch (ex) {
          case 1:
            exercise1();
            break;
          case 2:
            exercise2();
            break;
          case 3:
            exercise3();
            break;
          default:
            return;
        }
      } catch (IOException e) {

      } catch (WrongStudentName e) {
        System.out.println("Błędne imie studenta!");
      } catch (WrongStudentAge e) {
        System.out.println("Błędny wiek studenta!");
      } catch (WrongStudentDate e) {
        System.out.println("Błędna data!");
      } catch (NumberFormatException e) {
        System.out.println("Wprowadź poprawną liczbę!");
      } catch (WrongStudentParse e) {
        System.out.println("Błędny zapis danych studenta!");
      }
    }
  }

  public static String menu() {
    System.out.println("Wciśnij:");
    System.out.println("1 - aby dodać studenta");
    System.out.println("2 - aby wypisać wszystkich studentów");
    System.out.println("3 - aby wyszukać studenta po imieniu");
    System.out.println("0 - aby wyjść z programu");
    return scan.nextLine();
  }

  public static String ReadName() throws WrongStudentName {
    scan.nextLine();
    System.out.println("Podaj imie: ");
    String name = scan.nextLine();
    if (name.contains(" "))
      throw new WrongStudentName();

    return name;
  }

  public static int ReadAge() throws WrongStudentAge {
    scan.nextLine();
    System.out.println("Podaj wiek: ");
    int age = scan.nextInt();
    if (age < 0 || age >= 120)
      throw new WrongStudentAge();

    return age;
  }

  public static String ReadData() throws WrongStudentDate {
    scan.nextLine();
    System.out.println("Podaj datę urodzenia DD-MM-YYYY: ");
    String date = scan.nextLine();
    String[] dataDate = date.split("-");
    int dd = Integer.parseInt(dataDate[0]);
    int mm = Integer.parseInt(dataDate[1]);
    int yy = Integer.parseInt(dataDate[2]);
    if (dd < 0 || dd > 32 || mm < 0 || mm > 13 || yy < 1900 || yy > 2023)
      throw new WrongStudentDate();

    return date;
  }

  public static void exercise1() throws IOException, WrongStudentName, WrongStudentAge, WrongStudentDate {
    var name = ReadName();
    var age = ReadAge();
    scan.nextLine();
    var date = ReadData();
    (new Service()).addStudent(new Student(name, age, date));
  }

  public static void exercise2() throws IOException, WrongStudentParse {

    var students = (new Service()).getStudents();
    for (Student current : students) {
      System.out.println(current.ToString());
    }
  }

  public static void exercise3() throws IOException, WrongStudentParse {
    scan.nextLine();
    System.out.println("Podaj imie: ");
    var name = scan.nextLine();
    var wanted = (new Service()).findStudentByName(name);
    if (wanted == null)
      System.out.println("Nie znaleziono...");
    else {
      System.out.println("Znaleziono: ");
      System.out.println(wanted.ToString());
    }
  }
}
