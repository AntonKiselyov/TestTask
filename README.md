# Тестовое задание

Клиент (Retro) отправляет на сервис строку типа: (*)-(+), которая описывает арифметическое выражение

Сервис (jersey-service) парсит выражение и получает дерево, описывающее результат выражение, отправляет дерево в формате JSON клиенту

Клиент с помощью jackson нотаций парсит полученный JSON и записывает его в структуру ExpressionNode


Например, (+)-(*)

Получим:
```json
{
	"expression": "(+)-(*)",
	"nodes": [{
		"expression": "(+)",
		"nodes": [{
			"expression": "("
		}, {
			"expression": "+",
			"nodes": [{
				"expression": "+"
			}]
		}, {
			"expression": ")"
		}]
	}, {
		"expression": "-"
	}, {
		"expression": "(*)",
		"nodes": [{
			"expression": "("
		}, {
			"expression": "*",
			"nodes": [{
				"expression": "*"
			}]
		}, {
			"expression": ")"
		}]
	}]
}
```
```java
ExpressionNode {'(+)-(*)',[ExpressionNode {'(+)',[ExpressionNode {'(',null}, ExpressionNode {'+',[ExpressionNode {'+',null}]}, ExpressionNode {')',null}]}, ExpressionNode {'-',null}, ExpressionNode {'(*)',[ExpressionNode {'(',null}, ExpressionNode {'*',[Expression {'*',null}]}, Expression {')',null}]}]}
```
