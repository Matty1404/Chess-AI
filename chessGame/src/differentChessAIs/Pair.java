package differentChessAIs;

public class Pair<S, T> {

  S valueA;
  T valueB;

  public Pair(S valueA, T valueB) {
    this.valueA = valueA;
    this.valueB = valueB;
  }

  public S getValueA() {
    return valueA;
  }

  public T getValueB() {
    return valueB;
  }

  public void setValueA(S valueA) {
    this.valueA = valueA;
  }

  public void setValueB(T valueB) {
    this.valueB = valueB;
  }
}
