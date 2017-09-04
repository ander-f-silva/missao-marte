package br.com.mm.infraestrutura.repositorio;

public class Entidade<T, E> {

    private T id;
    private E[] elementos;

    public Entidade(T id, E[] elementos) {
        this.id = id;
        this.elementos = elementos;
    }

    public T getId() {
        return id;
    }

    public E[] getElementos() {
        return elementos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entidade<?, ?> entidade = (Entidade<?, ?>) o;

        return id.equals(entidade.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
