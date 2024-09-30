package MovieManager.MovieList;

public class MoviesList {
    private LLNode actPtr;
    private LLNode head;
    private LLNode tail;
    private int size;

    public MoviesList() {
        actPtr = null;
        head = null;
        tail = null;
        size = 0;
    }

    public void add(Movies Movie, int Code) {
        if (head == null) 
        {
            head = new LLNode(Movie, Code);
            actPtr = head;
            tail = head;
            size = 1;
        } 
        else
        {
            tail.setNext(new LLNode(Movie, Code));
            tail.getNext().setLast(tail);
            tail = tail.getNext();
            size++;
        }
    }

    public void remove(int Code) {
        if (actPtr.getCode() == Code) {
            actPtr = actPtr.getNext() != null ? actPtr.getNext() : actPtr.getLast();
        }

        if (head.getCode() == Code) {
            head = head.getNext();
            if (head != null) head.setLast(null);
            size--;
        } else if (tail.getCode() == Code) {
            tail = tail.getLast();
            if (tail != null) tail.setNext(null);
            size--;
        } else {
            remove(Code, head);
        }

        if (head == null || tail == null) {
            head = null;
            tail = null;
        }
    }

    private void remove(int Code, LLNode actPtr) {
        if (actPtr.getNext().getCode() == Code) {
            actPtr.getNext().getNext().setLast(actPtr);
            actPtr.setNext(actPtr.getNext().getNext());
            size--;
        } else if (actPtr.getNext() != null) remove(Code, actPtr.getNext());
    }

    public Movies[] getNextMovieList()
    {
        if (head == null) return null;
        int IniCode = actPtr.getCode();
        
        for (int i = 0; i < 10; i++) {
            if (actPtr.getNext() == null) break;
            else actPtr = actPtr.getNext();
        }

        if (IniCode == actPtr.getCode()) return null;
        return getCurrentMovieList();
   }

    public Movies[] getLastMovieList()
    {
        if (head == null) return null;
        int IniCode = actPtr.getCode();

        for (int i = 0; i < 10; i++) {
            if (actPtr.getLast() == null) break;
            else actPtr = actPtr.getLast();
        }

        if (IniCode == actPtr.getCode()) return null;
        return getCurrentMovieList();
    }

    public Movies[] getCurrentMovieList() {
        if (head == null) return null;
        Movies[] MList = new Movies[10];
        LLNode auxNode = actPtr;

        for (int i = 0; i < 10; i++) {
            MList[i] = auxNode.getMovie();
            if (auxNode.getNext() == null) break;
            else auxNode = auxNode.getNext();
        }

        return MList;
    }

    public void alphabeticalSort() {
        if (head == tail) return;
        LLNode tempNode = null;

        for (LLNode Node = head; Node != tail; Node = Node.getNext()) {

            for (LLNode auxNode = Node.getNext(); auxNode != tail.getNext(); auxNode = auxNode.getNext()) {
                if (Node.getCode() > auxNode.getCode()) tempNode = auxNode;
            }
            changeData(Node, tempNode);
        }
        
    }
    
    private void changeData(LLNode N1, LLNode N2) {
        if (N1.getCode() == head.getCode()) {
            head.setMovie(N2.getMovie());
            head.setCode(N2.getCode());
        } else if (N1.getCode() == tail.getCode()) {
            tail.setMovie(N2.getMovie());
            tail.setCode(N2.getCode());
        } else {
            N1.setMovie(N2.getMovie());
            N1.setCode(N2.getCode());
        }
    }

    private class LLNode
    {
        private Movies Movie;
        private int Code;
        private LLNode next;
        private LLNode last;

        public LLNode(Movies _Movie, int _Code) {
            Movie = _Movie;
            Code = _Code;
            last = null;
            next = null;
        }

        protected void setMovie(Movies _Movie)
        { Movie = _Movie; }

        protected Movies getMovie()
        { return Movie; }
        
        protected void setCode(int _Code) 
        { Code = _Code; }

        protected int getCode() 
        { return Code; }

        protected void setNext(LLNode _next)
        { next = _next; }

        protected LLNode getNext()
        { return next; }

        protected void setLast(LLNode _last)
        { last = _last; }

        protected LLNode getLast()
        { return last; }

    }

    public int getSize()
    { return size; }

}
