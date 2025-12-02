# Лабораторна робота 4: Зоопарк з використанням Generics

## Опис проекту

Цей проект реалізує систему управління зоопарком з використанням узагальненого програмування (generics) в Java. Система дозволяє створювати вольєри для різних видів тварин з типобезпечними обмеженнями.

## Структура проекту

```
lab-4/
├── src/
│   ├── animals/          # Ієрархія тварин
│   │   ├── Animal.java   # Базовий абстрактний клас
│   │   ├── Mammal.java   # Абстрактний клас ссавців
│   │   ├── Bird.java     # Абстрактний клас птахів
│   │   ├── Lion.java     # Лев
│   │   ├── Ungulate.java # Абстрактний клас копитних
│   │   ├── Zebra.java    # Зебра
│   │   ├── Giraffe.java  # Жирафа
│   │   └── Eagle.java    # Орел
│   ├── cages/            # Ієрархія вольєрів
│   │   ├── Cage.java            # Базовий узагальнений клас вольєра
│   │   ├── MammalCage.java      # Вольєр для ссавців
│   │   ├── BirdCage.java        # Вольєр для птахів
│   │   ├── LionCage.java        # Вольєр для левів
│   │   └── UngulateCage.java    # Вольєр для копитних
│   ├── exceptions/       # Виняткові ситуації
│   │   ├── CageFullException.java
│   │   └── AnimalNotFoundException.java
│   └── zoo/
│       ├── Zoo.java      # Клас зоопарку
│       └── Main.java     # Головний клас для демонстрації
└── tests/
    └── ZooTest.java      # Модульні тести
```

## Ієрархія тварин

```
Animal (абстрактний)
├── Mammal (абстрактний)
│   ├── Lion
│   └── Ungulate (абстрактний)
│       ├── Zebra
│       └── Giraffe
└── Bird (абстрактний)
    └── Eagle
```

## Ієрархія вольєрів

```
Cage<T extends Animal> (узагальнений базовий клас)
├── MammalCage<T extends Mammal>
│   ├── LionCage (спеціалізований для левів)
│   └── UngulateCage (спеціалізований для копитних)
└── BirdCage (спеціалізований для птахів)
```

## Функціональність

### Вольєри (Cage)

Кожен вольєр має наступні можливості:

- **Максимальна ємність**: Визначається при створенні вольєра
- `getCapacity()`: Повертає максимальну кількість місць у вольєрі
- `getOccupiedPlaces()`: Повертає кількість зайнятих місць
- `addAnimal(T animal)`: Додає тварину до вольєра
  - Викидає `CageFullException`, якщо вольєр заповнений
- `removeAnimal(T animal)`: Видаляє тварину з вольєра
  - Викидає `AnimalNotFoundException`, якщо тварини немає у вольєрі
- `getAnimals()`: Повертає список тварин у вольєрі

### Обмеження типів

Завдяки використанню generics, система забезпечує типобезпеку:

- **LionCage**: Може містити тільки левів (`Lion`)
- **BirdCage**: Може містити тільки птахів (`Bird` та його підкласи)
- **UngulateCage**: Може містити тільки копитних (`Ungulate` та його підкласи: `Zebra`, `Giraffe`)

### Зоопарк (Zoo)

Клас `Zoo` використовує wildcards (`? extends Animal`) для роботи з вольєрами різних типів:

- `addCage(Cage<? extends Animal> cage)`: Додає вольєр до зоопарку
- `getCountOfAnimals()`: Підраховує загальну кількість тварин у всіх вольєрах
- `getCages()`: Повертає список всіх вольєрів

## Компіляція та запуск

### Компіляція

```bash
javac -d build -sourcepath src -cp "lib/junit-platform-console-standalone-1.9.2.jar" src/animals/*.java src/cages/*.java src/exceptions/*.java src/zoo/*.java tests/*.java
```

### Запуск головного класу

```bash
java -cp build src.zoo.Main
```

### Налаштування та запуск тестів JUnit

Проект використовує **JUnit 5 (Jupiter)** для модульних тестів.

#### Крок 1: Завантаження JUnit 5

1. Завантажте `junit-platform-console-standalone-1.9.2.jar` з офіційного сайту

2. Створіть папку `lib` у корені проекту та помістіть туди завантажений JAR файл:
   ```
   lab-4/
   └── lib/
       └── junit-platform-console-standalone-1.9.2.jar
   ```

#### Крок 2: Компіляція з JUnit

```powershell
javac -d build -sourcepath src -cp "lib/junit-platform-console-standalone-1.9.2.jar" src/animals/*.java src/cages/*.java src/exceptions/*.java src/zoo/*.java tests/*.java
```

#### Крок 3: Запуск тестів

```powershell
java -cp "build;lib/junit-platform-console-standalone-1.9.2.jar" org.junit.platform.console.ConsoleLauncher --class-path build --select-class tests.ZooTest
```

Або для запуску всіх тестів у пакеті `tests`:

```bash
java -cp "build;lib/junit-platform-console-standalone-1.9.2.jar" org.junit.platform.console.ConsoleLauncher --class-path build --select-package tests
```

## Виняткові ситуації

### CageFullException

Виникає при спробі додати тварину до заповненого вольєра:

```java
try {
    lionCage.addAnimal(new Lion("Extra"));
} catch (CageFullException e) {
    System.out.println(e.getMessage());
}
```

### AnimalNotFoundException

Виникає при спробі видалити неіснуючу тварину:

```java
try {
    birdCage.removeAnimal(new Eagle("NonExistent"));
} catch (AnimalNotFoundException e) {
    System.out.println(e.getMessage());
}
```

## Модульне тестування з JUnit

Проект використовує **JUnit 5 (Jupiter)** для модульних тестів. Тести знаходяться в класі `ZooTest` та покривають:

1. ✅ Створення та ємність вольєрів різних типів
2. ✅ Додавання та видалення тварин
3. ✅ Підрахунок тварин у зоопарку
4. ✅ Обробку виняткових ситуацій (`CageFullException`, `AnimalNotFoundException`)
5. ✅ Типобезпеку generics (перевірка, що не можна додати неправильний тип тварини)

## Технічні особливості

1. **Generics**: Використання узагальненого програмування для типобезпеки
2. **Wildcards**: Застосування `? extends Animal` у класі `Zoo` для роботи з різними типами вольєрів
3. **Наслідування**: Правильна ієрархія класів з використанням абстрактних класів
4. **Exception Handling**: Коректна обробка виняткових ситуацій
5. **JUnit Testing**: Модульне тестування з використанням фреймворку JUnit 5 (без Maven/Gradle)
