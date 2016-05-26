# Тестовое задание

Клиент (Retro) принимает в командной строке следующий список входных аргументов: <выражение> <количество переменных> <..числовые значения переменных..>, Например: (a+b)*(c-d) 4 10 5 10 6

Сервис (jersey-service) парсит выражение "(a+b)*(c-d)" и получает дерево, описывающее результат выражение, а также список переменных выражения, отправляет дерево и переменные в формате JSON клиенту

Клиент с помощью jackson нотаций парсит полученный JSON и записывает его в структуру ResultStructure, содержащую ExpressionNode - дерево и Variables - переменные.


Например, (a+b)*(c-d)

Получим:
```json
{
  "variables": [
    "a",
    "b",
    "c",
    "d"
  ],
  "tree": {
    "expression": "(a+b)*(c-d)",
    "nodes": [
      {
        "expression": "(a+b)",
        "nodes": [
          {
            "expression": "("
          },
          {
            "expression": "a+b",
            "nodes": [
              {
                "expression": "a",
                "nodes": [
                  {
                    "expression": "a"
                  }
                ]
              },
              {
                "expression": "+"
              },
              {
                "expression": "b",
                "nodes": [
                  {
                    "expression": "b"
                  }
                ]
              }
            ]
          },
          {
            "expression": ")"
          }
        ]
      },
      {
        "expression": "*"
      },
      {
        "expression": "(c-d)",
        "nodes": [
          {
            "expression": "("
          },
          {
            "expression": "c-d",
            "nodes": [
              {
                "expression": "c",
                "nodes": [
                  {
                    "expression": "c"
                  }
                ]
              },
              {
                "expression": "-"
              },
              {
                "expression": "d",
                "nodes": [
                  {
                    "expression": "d"
                  }
                ]
              }
            ]
          },
          {
            "expression": ")"
          }
        ]
      }
    ]
  }
}
```

После парсинга полученного JSON объекта, получается следующий объект:

```java
ResultStructure {'Variables {'{a=null, b=null, c=null, d=null}'}',ExpressionNode {'(a+b)*(c-d)',[ExpressionNode {'(a+b)',[ExpressionNode {'(',null}, ExpressionNode {'a+b',[ExpressionNode {'a',[ExpressionNode {'a',null}]}, ExpressionNode {'+',null}, ExpressionNode {'b',[ExpressionNode {'b',null}]}]}, ExpressionNode {')',null}]}, ExpressionNode {'*',null}, ExpressionNode {'(c-d)',[ExpressionNode {'(',null}, ExpressionNode {'c-d',[ExpressionNode {'c',[ExpressionNode {'c',null}]}, ExpressionNode {'-',null}, ExpressionNode {'d',[ExpressionNode {'d',null}]}]}, ExpressionNode {')',null}]}]}}

```

Клиент делает рекурсивный обход дерева и подставляет на место переменных введенные в командной строке значения :

(a+b)*(c-d) = (10+5)*(10-6) = 60


