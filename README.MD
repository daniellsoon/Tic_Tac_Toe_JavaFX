# TicTacToe Game in JavaFX

This project is presents game TicTacToe in JavaFX with `Save/Load` and `Ranking` possibility. 
Game allows play in 2 modes: `Player vs Computer` and `Player vs Player`.

```java
 public void setState(int state) {
        if (state == 0) {
            this.setGraphic(null);
            this.value = 0;
        } else  if (state == 1) {
            this.setGraphic(new ImageView(cross));
            this.value = 1;
        } else if (state == -1){
            this.setGraphic(new ImageView(circle));
            this.value = -1;
        } else {
            System.out.println("setState() called with illegal state");
        }
    }
```

## Installation

You can open it with simply Java 1.8 compilation. 

## Features

* my first bigger project
* good to learn Java + JavaFX
* gives some fun!

## Contribute

* Issue Tracker: https://github.com/daniellsoon/Tic_Tac_Toe_JavaFX/issues
* Source Code: https://github.com/daniellsoon/Tic_Tac_Toe_JavaFX
* Tests: manual

## License

This project is released under the MIT Licence. See the bundled LICENSE file for details.

## Author

Daniel Adamiec