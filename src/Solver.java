import java.awt.*;

class Solver {
    private Character[][] grid;
    private int gridWidth;
    private int gridHeight;

    private int wordStrike;
    private Point wordStart;
    private int lastWordStrike;

    Solver(Character[][] grid, int gridWidth, int gridHeight) {
        this.grid = grid;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
    }

    Solution solve(String word) {
        Solution solution;

        solution = searchVertically(word);
        if (solution != null) {
            System.out.println("Found word " + word + " vertically");
            return solution;
        }

        solution = searchHorizontally(word);
        if (solution != null) {
            System.out.println("Found word " + word + " horizontally");
            return solution;
        }

        solution = searchDiagonally(word);
        if (solution != null) {
            System.out.println("Found word " + word + " diagonally");
            return solution;
        }

        return null;
    }

    private Solution searchHorizontally(String word) {
        wordStart = null;

        for (int j = 0; j < gridHeight; ++j) {
            wordStrike = 0;

            for (int i = 0; i < gridWidth; ++i) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, i, j);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    i = wordStart.x;
                }
            }

            wordStrike = 0;

            for (int i = gridWidth - 1; i >= 0; --i) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, i, j);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    i = wordStart.x;
                }
            }
        }

        return null;
    }

    private Solution searchVertically(String word) {
        wordStart = null;

        for (int i = 0; i < gridWidth; ++i) {
            wordStrike = 0;

            for (int j = 0; j < gridHeight; ++j) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, i, j);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    j = wordStart.y;
                }
            }

            wordStrike = 0;

            for (int j = gridHeight - 1; j >= 0; --j) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, i, j);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    j = wordStart.y;
                }
            }
        }

        return null;
    }

    private Solution searchDiagonally(String word) {
        Solution solution;

        solution = searchDiagonallyBottomLeftHeight(word);
        if (solution != null) {
            return solution;
        }

        solution = searchDiagonallyBottomLeftWidth(word);
        if (solution != null) {
            return solution;
        }

        solution = searchDiagonallyTopLeftHeight(word);
        if (solution != null) {
            return solution;
        }

        solution = searchDiagonallyTopLeftWidth(word);
        if (solution != null) {
            return solution;
        }

        solution = searchDiagonallyTopRightHeight(word);
        if (solution != null) {
            return solution;
        }

        solution = searchDiagonallyTopRightWidth(word);
        if (solution != null) {
            return solution;
        }

        solution = searchDiagonallyBottomRightHeight(word);
        if (solution != null) {
            return solution;
        }

        solution = searchDiagonallyBottomRightWidth(word);
        if (solution != null) {
            return solution;
        }

        return null;
    }

    private Solution searchDiagonallyBottomLeftHeight(String word) {
        wordStart = null;

        int i;

        for (int startJ = gridHeight - 1; startJ >= 0; --startJ) {
            i = 0;
            wordStrike = 0;

            for (int currentJ = startJ; currentJ >= 0; --currentJ) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, i, currentJ);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    i = wordStart.x;
                    currentJ = wordStart.y;
                }

                ++i;

                if (i >= gridWidth) {
                    break;
                }
            }
        }

        return null;
    }

    private Solution searchDiagonallyBottomLeftWidth(String word) {
        wordStart = null;

        int j;

        for (int startI = 1; startI < gridWidth; ++startI) {
            j = gridHeight - 1;
            wordStrike = 0;

            for (int currentI = startI; currentI < gridWidth; ++currentI) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, currentI, j);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    currentI = wordStart.x;
                    j = wordStart.y;
                }

                --j;

                if (j < 0) {
                    break;
                }
            }
        }

        return null;
    }

    private Solution searchDiagonallyTopLeftHeight(String word) {
        wordStart = null;

        int i;

        for (int startJ = 0; startJ < gridHeight; ++startJ) {
            i = 0;
            wordStrike = 0;

            for (int currentJ = startJ; currentJ < gridHeight; ++currentJ) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, i, currentJ);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    i = wordStart.x;
                    currentJ = wordStart.y;
                }

                ++i;

                if (i >= gridWidth) {
                    break;
                }
            }
        }

        return null;
    }

    private Solution searchDiagonallyTopLeftWidth(String word) {
        wordStart = null;

        int j;

        for (int startI = 1; startI < gridWidth; ++startI) {
            j = 0;
            wordStrike = 0;

            for (int currentI = startI; currentI < gridWidth; ++currentI) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, currentI, j);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    currentI = wordStart.x;
                    j = wordStart.y;
                }

                ++j;

                if (j >= gridHeight) {
                    break;
                }
            }
        }

        return null;
    }

    private Solution searchDiagonallyTopRightHeight(String word) {
        wordStart = null;

        int i;

        for (int startJ = 0; startJ < gridHeight; ++startJ) {
            i = gridWidth - 1;
            wordStrike = 0;

            for (int currentJ = startJ; currentJ < gridHeight; ++currentJ) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, i, currentJ);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    i = wordStart.x;
                    currentJ = wordStart.y;
                }

                --i;

                if (i < 0) {
                    break;
                }
            }
        }

        return null;
    }

    private Solution searchDiagonallyTopRightWidth(String word) {
        wordStart = null;

        int j;

        for (int startI = gridWidth - 2; startI >= 0; --startI) {
            j = 0;
            wordStrike = 0;

            for (int currentI = startI; currentI >= 0; --currentI) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, currentI, j);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    currentI = wordStart.x;
                    j = wordStart.y;
                }

                ++j;

                if (j >= gridHeight) {
                    break;
                }
            }
        }

        return null;
    }

    private Solution searchDiagonallyBottomRightHeight(String word) {
        wordStart = null;

        int i;

        for (int startJ = gridHeight - 1; startJ >= 0; --startJ) {
            i = gridWidth - 1;
            wordStrike = 0;

            for (int currentJ = startJ; currentJ >= 0; --currentJ) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, i, currentJ);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    i = wordStart.x;
                    currentJ = wordStart.y;
                }

                --i;

                if (i < 0) {
                    break;
                }
            }
        }

        return null;
    }

    private Solution searchDiagonallyBottomRightWidth(String word) {
        wordStart = null;

        int j;

        for (int startI = gridWidth - 2; startI >= 0; --startI) {
            j = gridHeight - 1;
            wordStrike = 0;

            for (int currentI = startI; currentI >= 0; --currentI) {
                lastWordStrike = wordStrike;
                Solution potentialSolution = handleWordStrike(word, currentI, j);
                if (potentialSolution != null) {
                    return potentialSolution;
                } else if (lastWordStrike > 0 && wordStrike == 0) {
                    currentI = wordStart.x;
                    j = wordStart.y;
                }

                --j;

                if (j < 0) {
                    break;
                }
            }
        }

        return null;
    }

    private Solution handleWordStrike(String word, int i, int j) {
        wordStrike = wordStrike < word.length() ? word.charAt(wordStrike) == grid[j][i] ? wordStrike + 1 : 0 : 0;

        if (wordStrike == 1) {
            wordStart = new Point(i, j);
        }

        if (wordStrike == word.length()) {
            Point wordEnd = new Point(i, j);
            return new Solution(wordStart, wordEnd);
        }

        return null;
    }
}
