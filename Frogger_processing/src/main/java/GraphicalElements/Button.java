package GraphicalElements;

import processing.core.PApplet;

/**
 * Spécifie un type de case particulier avec lequel on peut intéragir
 */
public class Button extends Element{
    private int left;
    private int top;
    private int right;
    private int bottom;
    private String text;

    /**
     * @param P Une instance de PApplet qui lance une IHM et qui sert de support à l'affichage du bouton et à ses actions
     * @param left L'abscisse du côté latéral gauche du bouton
     * @param top L'ordonnée du côté supérieur du bouton
     * @param right L'abscisse du côté latéral droit du bouton
     * @param bottom L'ordonnée du côté inférieur du bouton
     * @param text Le texte affiché sur le bouton
     */
    public Button(PApplet P, int left, int top, int right, int bottom, String text) {
        super(P);
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.text = text;
    }

    @Override
    public void create_case(float x, float y, float w, float h, float r, float g, float b) {
        super.create_case(x, y, w, h, r, g, b);
    }

    @Override
    public void create_text(String text, int size, int x, int y, int r, int g, int b) {
        super.create_text(text, size, x, y, r, g, b);
    }

    /**
     * Affiche le bouton tout en le colorant lorsque la souris passe dessus
     */
    public void show(){
        this.create_case(left, top, right, bottom, 255,255,255);
        this.create_text(text, 25, left+(right-left)/3-10, top-(top-bottom)/4-5, 0,0,0);
        if (P.mouseX>left && P.mouseX<right &&P.mouseY<top &&P.mouseY>bottom){
            this.create_case(left, top, right, bottom, 200,0,0);
            this.create_text(text, 25, left+(right-left)/3-10, top-(top-bottom)/4-5, 0,0,0);
        }
    }
    /**
     * Renvoie true lorsque l'on clique sur le bouton, et false dans le cas contraire
     */
    public boolean click_event(){
        return (P.mouseX>left && P.mouseX<right &&P.mouseY<top &&P.mouseY>bottom&& P.mousePressed);

    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
