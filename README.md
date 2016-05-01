# Jersey

Клиент (Retro) отправляет на сервис строку типа: (*)-(+), которая описывает арифметическое выражение

Сервис (jersey-service) парсит выражение и получает дерево, описывающее результат выражение, отправляет дерево в формате JSON клиенту

Клиент с помощью jackson нотаций парсит полученный JSON и записывает его в структуру MyTreeNode


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

MyTreeNode {'(+)-(*)',[MyTreeNode {'(+)',[MyTreeNode {'(',null}, MyTreeNode {'+',[MyTreeNode {'+',null}]}, MyTreeNode {')',null}]}, MyTreeNode {'-',null}, MyTreeNode {'(*)',[MyTreeNode {'(',null}, MyTreeNode {'*',[MyTreeNode {'*',null}]}, MyTreeNode {')',null}]}]}
