/**
 * Реализация линейного двунаправленного списка.
 * 
 * @param <T> тип элементов, хранящихся в списке
 */
public class DoubleLinkedList<T> {
    
    /** Массив для хранения узлов (внутреннее хранилище) */
    @SuppressWarnings("unchecked")
    private Node<T>[] nodes = (Node<T>[]) new Node[10];
    
    /** Количество элементов в списке */
    private int size = 0;
    
    /** Индекс текущего элемента (указатель) */
    private int currentIndex = -1;
    
    /**
     * Конструктор по умолчанию. Создаёт пустой список.
     */
    public DoubleLinkedList() {
        // Инициализация массива
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = null;
        }
    }
    
    /**
     * Проверяет, пуст ли список.
     * 
     * @return true, если список пуст, иначе false
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Возвращает количество элементов в списке.
     * 
     * @return размер списка
     */
    public int size() {
        return size;
    }
    
    /**
     * Увеличивает ёмкость массива при необходимости.
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size >= nodes.length) {
            Node<T>[] newNodes = (Node<T>[]) new Node[nodes.length * 2];
            System.arraycopy(nodes, 0, newNodes, 0, size);
            nodes = newNodes;
        }
    }
    
    /**
     * Устанавливает указатель в начало списка.
     * 
     * @throws IllegalStateException если список пуст
     */
    public void moveToFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Невозможно установить указатель: список пуст");
        }
        currentIndex = 0;
    }
    
    /**
     * Устанавливает указатель в конец списка.
     * 
     * @throws IllegalStateException если список пуст
     */
    public void moveToLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Невозможно установить указатель: список пуст");
        }
        currentIndex = size - 1;
    }
    
    /**
     * Добавляет элемент после текущего указателя.
     * Если список пуст, элемент становится единственным.
     * 
     * @param element элемент для добавления
     */
    public void addAfterCurrent(T element) {
        Node<T> newNode = new Node<>(element);
        
        if (isEmpty()) {
            nodes[0] = newNode;
            size = 1;
            currentIndex = 0;
            return;
        }
        
        if (currentIndex < 0 || currentIndex >= size) {
            throw new IllegalStateException("Указатель не установлен");
        }
        
        ensureCapacity();
        
        // Сдвигаем элементы вправо
        for (int i = size; i > currentIndex + 1; i--) {
            nodes[i] = nodes[i - 1];
        }
        
        // Обновляем связи между узлами
        newNode.prev = nodes[currentIndex];
        newNode.next = nodes[currentIndex + 1];
        
        if (nodes[currentIndex + 1] != null) {
            nodes[currentIndex + 1].prev = newNode;
        }
        nodes[currentIndex].next = newNode;
        
        nodes[currentIndex + 1] = newNode;
        size++;
    }
    
    /**
     * Добавляет элемент перед текущим указателем.
     * Если список пуст, элемент становится единственным.
     * 
     * @param element элемент для добавления
     */
    public void addBeforeCurrent(T element) {
        Node<T> newNode = new Node<>(element);
        
        if (isEmpty()) {
            nodes[0] = newNode;
            size = 1;
            currentIndex = 0;
            return;
        }
        
        if (currentIndex < 0 || currentIndex >= size) {
            throw new IllegalStateException("Указатель не установлен");
        }
        
        ensureCapacity();
        
        // Сдвигаем элементы вправо, начиная с currentIndex
        for (int i = size; i > currentIndex; i--) {
            nodes[i] = nodes[i - 1];
        }
        
        // Обновляем связи между узлами
        // Предыдущий узел (может быть null, если вставляем в начало)
        Node<T> prevNode = (currentIndex > 0) ? nodes[currentIndex - 1] : null;
        Node<T> currNode = nodes[currentIndex];
        
        newNode.prev = prevNode;
        newNode.next = currNode;
        
        if (prevNode != null) {
            prevNode.next = newNode;
        }
        currNode.prev = newNode;
        
        // Вставляем новый узел
        nodes[currentIndex] = newNode;
        size++;
        // Указатель остаётся на том же элементе (новый встал перед ним)
        currentIndex++;
    }
    
    /**
     * Удаляет элемент после текущего указателя.
     * 
     * @return удалённый элемент
     * @throws IllegalStateException если удаление невозможно
     */
    public T removeAfterCurrent() {
        if (isEmpty()) {
            throw new IllegalStateException("Невозможно удалить: список пуст");
        }
        
        if (currentIndex < 0 || currentIndex >= size) {
            throw new IllegalStateException("Указатель не установлен");
        }
        
        if (currentIndex + 1 >= size) {
            throw new IllegalStateException("Нет элемента после указателя");
        }
        
        Node<T> toRemove = nodes[currentIndex + 1];
        T removed = toRemove.data;
        
        // Обновляем связи
        nodes[currentIndex].next = toRemove.next;
        if (toRemove.next != null) {
            toRemove.next.prev = nodes[currentIndex];
        }
        
        // Сдвигаем элементы влево
        for (int i = currentIndex + 1; i < size - 1; i++) {
            nodes[i] = nodes[i + 1];
        }
        
        nodes[size - 1] = null;
        size--;
        
        return removed;
    }
    
    /**
     * Удаляет элемент перед текущим указателем.
     * 
     * @return удалённый элемент
     * @throws IllegalStateException если удаление невозможно
     */
    public T removeBeforeCurrent() {
        if (isEmpty()) {
            throw new IllegalStateException("Невозможно удалить: список пуст");
        }
        
        if (currentIndex < 0 || currentIndex >= size) {
            throw new IllegalStateException("Указатель не установлен");
        }
        
        if (currentIndex - 1 < 0) {
            throw new IllegalStateException("Нет элемента до указателя");
        }
        
        Node<T> toRemove = nodes[currentIndex - 1];
        T removed = toRemove.data;
        
        // Обновляем связи
        if (toRemove.prev != null) {
            toRemove.prev.next = nodes[currentIndex];
        }
        nodes[currentIndex].prev = toRemove.prev;
        
        // Сдвигаем элементы влево
        for (int i = currentIndex - 1; i < size - 1; i++) {
            nodes[i] = nodes[i + 1];
        }
        
        nodes[size - 1] = null;
        size--;
        currentIndex--;
        
        return removed;
    }
    
    /**
     * Перемещает указатель вправо (на следующий элемент).
     * 
     * @return true, если перемещение успешно, false если достигнут конец
     */
    public boolean moveRight() {
        if (currentIndex + 1 < size) {
            currentIndex++;
            return true;
        }
        return false;
    }
    
    /**
     * Перемещает указатель влево (на предыдущий элемент).
     * 
     * @return true, если перемещение успешно, false если достигнуто начало
     */
    public boolean moveLeft() {
        if (currentIndex - 1 >= 0) {
            currentIndex--;
            return true;
        }
        return false;
    }
    
    /**
     * Обменивает значения элементов до указателя и после указателя, если оба элемента существуют.
     * 
     * @return true, если обмен выполнен успешно, иначе false
     */
    public boolean swapBeforeAndAfter() {
        if (currentIndex - 1 < 0 || currentIndex + 1 >= size) {
            return false;
        }
        
        T temp = nodes[currentIndex - 1].data;
        nodes[currentIndex - 1].data = nodes[currentIndex + 1].data;
        nodes[currentIndex + 1].data = temp;
        
        return true;
    }
    
    /**
     * Возвращает текущий элемент (тот, на который указывает указатель).
     * 
     * @return текущий элемент
     * @throws IllegalStateException если список пуст или указатель не установлен
     */
    public T getCurrent() {
        if (isEmpty() || currentIndex < 0 || currentIndex >= size) {
            throw new IllegalStateException("Указатель не установлен или список пуст");
        }
        return nodes[currentIndex].data;
    }
    
    /**
     * Возвращает элемент после текущего указателя.
     * 
     * @return следующий элемент или null
     */
    public T getNext() {
        if (currentIndex + 1 < size) {
            return nodes[currentIndex + 1].data;
        }
        return null;
    }
    
    /**
     * Возвращает элемент перед текущим указателем.
     * 
     * @return предыдущий элемент или null
     */
    public T getPrev() {
        if (currentIndex - 1 >= 0) {
            return nodes[currentIndex - 1].data;
        }
        return null;
    }
    
    /**
     * Выводит список на экран в строковом представлении.
     * 
     * @return строковое представление списка
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for (int i = 0; i < size; i++) {
            if (i == currentIndex) {
                sb.append("|").append(nodes[i].data).append("|");
            } else {
                sb.append(nodes[i].data);
            }
            
            if (i < size - 1) {
                sb.append(" , ");
            }
        }
        
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Очищает список.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            nodes[i] = null;
        }
        size = 0;
        currentIndex = -1;
    }
}