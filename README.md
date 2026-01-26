### Hexlet tests and linter status:
[![Actions Status](https://github.com/AlexFrankiv/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/AlexFrankiv/java-project-71/actions)
[![SonarQube](https://github.com/AlexFrankiv/java-project-71/actions/workflows/build.yml/badge.svg)](https://github.com/AlexFrankiv/java-project-71/actions/workflows/build.yml)
[![Java CI with Gradle](https://github.com/AlexFrankiv/java-project-71/actions/workflows/gradle.yml/badge.svg)](https://github.com/AlexFrankiv/java-project-71/actions/workflows/gradle.yml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AlexFrankiv_java-project-71&metric=coverage)](https://sonarcloud.io/summary/new_code?id=AlexFrankiv_java-project-71)

Gendiff — это консольная утилита для определения различий между двумя файлами конфигурации. Поддерживает форматы JSON и YAML, а также предоставляет различные форматы вывода, включая стильный, плоский и JSON.

Использование:
```bash
gendiff [options] <filepath1> <filepath2>
```

Сравните два файла и выведите разницу.

Опции:
**[вывод -h, --help](https://asciinema.org/a/daTphApRu3284Ed5)**

Доступные форматы вывода:

**[JSON](https://asciinema.org/a/lD9TK78ERrb1C3Df)**
```
./app --format json file1.json file2.json
```

**[Plain](https://asciinema.org/a/whOft15IWOWGa29I)** 
```
./app --format plain file1.yml file2.yml
```

**[Stylish](https://asciinema.org/a/tBcG7wJSoqY1P8yz)**
```
./app file1.yml file2.yml
```
