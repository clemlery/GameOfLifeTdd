package model

class Grid(
    val cells: Array<Array<Cell>>,
    val width : Int,
    val height : Int,
) {
    /**
     * Retourne la cellule située aux coordonnées données dans la grille.
     *
     * @param x coordonnée horizontale de la cellule
     * @param y coordonnée verticale de la cellule
     * @throws IllegalArgumentException si la position spécifiée est en dehors
     * de la grid
     * @return la cellule correspondant à la position (x, y)
     */
    fun cellAt(x: Int, y: Int): Cell {
        require(x in 0 ..< this.width && y in 0 ..< this.height)
        return this.cells[x][y]
    }

    companion object {
        /**
         * Crée une grille initiale à partir des coordonnées des cellules vivantes.
         *
         * Toutes les cellules non spécifiées sont considérées comme mortes.
         * Cette méthode est principalement destinée aux tests afin de définir
         * facilement des configurations initiales du Jeu de la vie.
         *
         * @param aliveCells liste des coordonnées (x, y) des cellules vivantes
         * @param gridWidth largeur de la grille
         * @param gridHeight hauteur de la grille
         * @throws IllegalArgumentException si la hauteur ou la largeur ne sont pas des valeurs acceptables (ex : valeur négative ou trop grande)
         * @return une grille de taille gridWidth x gridHeight contenant les cellules vivantes et mortes correspondantes
         */
        fun ofAliveCells(vararg aliveCells: Pair<Int, Int>, gridWidth : Int, gridHeight : Int): Grid {
            require(aliveCells.isNotEmpty() && gridWidth > 0 && gridHeight > 0)

            val grid = empty(gridWidth, gridHeight)

            aliveCells.forEach { (x, y) ->
                require(x in 0..<gridWidth && y in 0 ..<gridHeight)
                grid.cells[x][y] = Cell(x, y, CellState.ALIVE)
            }

            return grid
        }

        /**
         * Crée une grille vide de taille gridWidth par gridHeight
         *
         * @param gridWidth largeur de la grille
         * @param gridHeight hauteur de la grille
         * @throws IllegalArgumentException si la hauteur ou la largeur ne sont pas des valeurs acceptables (ex : valeur négative ou trop grande
         * @return une grille contenant des cellules mortes de taille gridWidth x gridHeight
         */
        fun empty(gridWidth: Int, gridHeight: Int) : Grid {
            require(gridWidth > 0 && gridHeight > 0)

            return Grid(
                cells = Array(gridWidth) { x ->
                    Array(gridHeight) { y ->
                        Cell(x, y, CellState.DEAD)
                    }
                },
                gridWidth,
                gridHeight
            )
        }
    }
}