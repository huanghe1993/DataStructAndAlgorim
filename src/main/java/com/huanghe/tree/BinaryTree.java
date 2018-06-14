package com.huanghe.tree;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: River
 * @Date:Created in  18:55 2018/6/10
 * @Description: 二叉树的构建及相关的操作   二叉树的输入输出格式采用广义表表达式形式，内部表示采用左孩子右孩子的链式存储。
 * 广义表：如A(B,)表示一颗有2个节点的树。其中根的data值为A，其左孩子为叶子节点，data值为B，右孩子为空。
 * 以广义表形式的字符串构建二叉树：'()'前表示根结点，括号中左右子树用逗号隔开，逗号不能省略
 */
public class BinaryTree<E> {

    public Node<E> getRoot() {
        return root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    //二叉树的根结点
    private Node<E> root;

    //二叉树的广义表达示
    private String expression;

    public BinaryTree() {
        super();
    }

    /**
     * 构造函数，根据前序和中序的遍历的结果构造二叉树
     */
    public BinaryTree(String s1, String s2, boolean isPreIn) {
        if (isPreIn) {
            root = createBinaryTreeByPreAndIn(s1, s2);
        } else {
            root = createBinaryTreeByPostAndIn(s1, s2);
        }


    }

    /**
     * 用于检测二叉树广义表表达式合法性的编译了的正则表达式:
     * 合法的广义表表达式可以是：
     * [1] 基本表达式： A，A(,), A(B,), A(,B), A(B,C)
     * [2] 上述的 A，B，C 可以是 [1] 中的基本表达式
     * [3] A,B,C 可允许的取值范围由应用决定，这里仅允许是 大小写字母，数字，+-/*%
     * [4] 表达式中不含任何空格符，因此，在检查表达式之前，必须确保表达式中不含空格符
     */
    private static String permittedChars = "[a-zA-Z0-9//+//-//*/////%]";
    private static String basicUnit = "[a-zA-Z0-9//+//-//*/////%//(//),]";
    private static Pattern basicPattern = Pattern.compile("" + "|" + permittedChars + "|" + permittedChars + "//(" + permittedChars + "?," + permittedChars + "?//)?");
    private static Pattern extendPattern = Pattern.compile(permittedChars + "//(" + basicUnit + "*," + basicUnit + "*//)");

    /**
     * separatorIndex : 求解广义表表达式中分割左右子树的分隔符的位置
     * 由于这里使用逗号分割左右子树，则当且仅当其位置应当满足：
     * 在该分割符之前，左括号的数目恰好比右括号的数目多1.
     *
     * @return 若存在满足条件的分隔符，则返回其位置；否则返回 -1
     */
    private static int separatorIndex(String expression) {
        int leftBracketCounter = 0, rightBacketCounter = 0;
        int index = 0;
        for (; index < expression.length(); index++) {
            char ch = expression.charAt(index);
            if (ch == '(') {
                leftBracketCounter++;
            } else if (ch == ')') {
                rightBacketCounter++;
            } else if (ch == ',') {
                if (leftBracketCounter == rightBacketCounter + 1) break;
            }
        }
        if (index < expression.length()) {
            return index;
        }
        return -1;
    }


    /**
     * checkValid: 判断给定二叉树的广义表表示是否合法有效
     *
     * @param expression 给定二叉树的广义表表示【字符串形式】
     * @return 如果给定的二叉树广义表表示合法有效，返回true; 否则，返回 false
     */
    private static boolean checkValid(String expression) {
        Matcher m = null;
        if (basicPattern.matcher(expression).matches())
            return true;
        else if ((m = extendPattern.matcher(expression)).matches()) {
            int index = separatorIndex(expression);
            if (index == -1) {  // 不存在能够分割二叉树广义表达式的左右子树表达式的逗号
                return false;
            }
            String rightEx = "";
            String leftEx = "";
            if (index > 2) {
                leftEx = expression.substring(2, index);  // 左子树的广义表达式
            }
            if (index < expression.length() - 2) {
                rightEx = expression.substring(index + 1, expression.length() - 1);  // 右子树的广义表达式
            }
            return checkValid(leftEx) && checkValid(rightEx);
        } else {
            return false;
        }
    }

    /**
     * 根据广义表达式创建二叉树：
     * <p>
     * 每当遇到字母，将要创建节点
     * 每当遇到“（”，表面要创建左孩子节点
     * 每当遇到“，”，表明要创建又孩子节点
     * 每当遇到“）”，表明要返回上一层节点
     * 广义表中“（”的数量正好是二叉树的层数
     *
     * @param expression
     */
    public void createBinararyTree(String expression) {
        LinkedList<Node> stack = new LinkedList<>();
        Node<E> node = null;
        Node<E> temp = null;
        Node<E> parent = null;
        boolean flag = false;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            switch (c) {
                case '(': //遇到左括号的时候把左括号前面的元素压入到栈中
                    stack.push(temp);
                    flag = true; //插入到左结点处
                    break;
                case ')': //当遇到右括号的时候进程弹栈的操作
                    stack.pop();
                    break;
                case ',':
                    flag = false;//插入到右结点处
                    break;
                default: // 创建根据内容创建节点
                    node = new Node(c);
                    break;
            }


            //如果树不存在，则创建树的根结点
            if (root == null) {
                root = node;
            }

            //为栈顶结点链入子女
            if (!stack.isEmpty()) {
                if (node != null) { // 当读入的是'('、')'、','字符时，略过
                    parent = stack.peek(); //返回栈顶的值
                    if (flag) {
                        parent.left = node;
                    } else {
                        parent.right = node;
                    }
                }
            }
            temp = node; // 用于入栈
            node = null; // node链入后，置空
        }
    }

    /**
     * 二叉树的层序遍历
     *
     * @return
     */
    public String levelOrder() {
        StringBuilder sb = new StringBuilder();
        LinkedList<Node<E>> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
            while (!queue.isEmpty()) {
                Node<E> temp = queue.pop();
                sb.append(temp.data).append(" ");
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return sb.toString().trim();
    }

    /**
     * 前序遍历二叉树（递归的方式）
     *
     * @param root
     * @return
     */
    public String preOrder(Node<E> root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(root.data + " ");//前序遍历根结点
            sb.append(preOrder(root.left));//前序遍历左子树
            sb.append(preOrder(root.right));//前序遍历右子树
        }
        return sb.toString();
    }

    /**
     * 前序遍历（迭代）：非线性结构（树），工作栈:当前结点入栈
     * 前序就是深度优先遍历，使用stack
     * <p>
     * 1)访问结点P，并将结点P入栈;
     * <p>
     * 2)判断结点P的左孩子是否为空，若为空，则取栈顶结点并进行出栈操作，并将栈顶结点的右孩子置为当前的结点P，循环至1);
     * 若不为空，则将P的左孩子置为当前的结点P;
     * 3)直到P为NULL并且栈为空，则遍历结束。
     *
     * @return
     */
    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        LinkedList<Node<E>> stack = new LinkedList<>();//工作栈，记录回退路径
        Node<E> node = root;

        while (node != null || !stack.isEmpty()) {
            if (node != null) {  //当前结点不为空
                sb.append(node.data + " "); //访问当前结点
                stack.push(node); //当前结点入栈
                node = node.left; //遍历左子树
            } else {
                node = stack.pop(); //弹出其父结点
                node = node.right; //遍历右子树
            }
        }
        return sb.toString();
    }

    /**
     * 非递归先序遍历二叉树,利用栈的结构
     * 返回的是一个集合
     * <p>
     * 以上的二叉树为例：先将A结点入栈此时的List为空，stack只有一个元素A，进入到while循环里面后，弹出栈顶元素A,并将A加入到List的集合中去
     * 然后依次把结点C和B依次加入到栈中，此时Stack从栈底到栈顶的元素依次为C,B;此时的栈Stack不为空，所以进入到while的循环中，弹出栈顶元素B,
     * 并且把B加入到List的集合中去，此时的List结合中的元素依次是A,B，然后B的右孩子和左孩子null和D分别的加入到栈中，此时的栈的从底到顶的顺序是C,null,D；
     * 同样由于栈不是为空，所以D元素弹出栈中，并且加入到LiSt的集合中去，此时的list的集合中的元素分别是A,B,D，并且把D的右孩子G和左孩子null分别的加入到集合栈
     * 中去，此时的栈从底到顶的元素的顺序是C，null,null,G ;弹出G并且加入到List的集合中去，，此时List的集合的元素分别是A,B，D，G，,栈的元素依次类推是C,null,null,null,null,
     * 然后就会一直的进行弹栈的操作，一直到A元素别弹出到栈的外面。。。。。接下来的操作和前面叙述的是相似的
     */
    public List<E> preOrderTraversal(Node<E> root) {
        List<E> resultList = new ArrayList<>();
        Stack<Node> treeStack = new Stack<>();
        if (root == null) //如果为空树则返回
            return resultList;
        treeStack.push(root);
        while (!treeStack.isEmpty()) {
            Node tempNode = treeStack.pop();
            if (tempNode != null) {
                resultList.add((E) tempNode.data);//访问根节点
                treeStack.push(tempNode.right); //入栈右孩子
                treeStack.push(tempNode.left);//入栈左孩子
            }
        }
        return resultList;
    }

    /**
     * 中序遍历二叉树（递归的方式进行）
     *
     * @param root
     * @return
     */
    public String inOrder(Node<E> root) {

        StringBuilder sb = new StringBuilder();

        if (root != null) {//递归的终止条件
            sb.append(inOrder(root.left));//遍历左子树
            sb.append(root.data + " ");//遍历根结点
            sb.append(inOrder(root.right));//遍历右子树
        }
        return sb.toString();
    }

    /**
     * 中序遍历的方式（迭代）
     * <p>
     * 对于任一结点P，
     * 1)若其左孩子不为空，则将P入栈并将P的左孩子置为当前的P，然后对当前结点P再进行相同的处理；
     * 2)若其左孩子为空，则取栈顶元素并进行出栈操作，访问该栈顶结点，然后将当前的P置为栈顶结点的右孩子；
     * 3)直到P为NULL并且栈为空则遍历结束
     * <p>
     * 分析：node开始的时候是根结点的位置不为空，所以需要把root结点压入到栈中，此时栈从栈底到栈顶的元素是A,遍历其左子树后会依次的把B,D压入到栈中
     * 此时的栈从栈底到栈顶的元素依次为A.B,D，当到D元素其左孩子为null，所以进入到else循环，父节点就是此时的D结点从栈中弹出来，并且会加入到sb中；
     * 然后访问D结点的右子树G结点，不为空所以压入栈中，此时的栈的元素依次为A,B，G,由于此时的G结点的左孩子为空，所以弹出G结点，并且接入到sb中，此时的
     * sb的元素是D,G ；G的右孩子为null，所以弹出栈顶的元素B,并且把B加入到sb中，此时的sb的值为D,G,B；由于B的右孩子为空，所以此时的栈弹出栈顶元素A,并把A
     * 加入到sb中，此时的sb的数据为D,G,B,A；此时的栈为空；由于A的右孩子C不为空加入到栈中，C的左孩子E不为空加入到栈中，E的左孩子为空，所以需要弹出E,
     * 并且把E加入到sb中，此时的sb的数值为D,G,B,A,E;由于E的右孩子为空，弹出C,并将C打印到sb，此时的sb为D,G,B,A,E,C；由于C的右孩子F不为空，压入栈，但是F的
     * 左孩子和为空，弹栈，并加入到sb中，此时栈为空而且node也为空结束循环
     *
     * @return
     */
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        LinkedList<Node<E>> stack = new LinkedList<>();//工作栈，记录回退路径
        Node<E> node = root;

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);  //当前结点不为空，则当前结点入栈
                node = node.left; //遍历其左子树
            } else {
                node = stack.pop(); //父节点弹栈
                sb.append(node.data + " ");//访问父节点
                node = node.right; //遍历右子树

            }
        }
        return sb.toString();
    }

    /**
     * 后续遍历（递归）
     *
     * @return
     */
    public String postOrder(Node<E> root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(postOrder(root.left));
            sb.append(postOrder(root.right));
            sb.append(root.data + " ");
        }
        return sb.toString();
    }

    /**
     * 后序遍历的非递归的形式（迭代）
     * <p>
     * 第一种思路：对于任一结点P，将其入栈，然后沿其左子树一直往下搜索，直到搜索到没有左孩子的结点，此时该结点出现在栈顶，
     * 但是此时不能将其出栈并访问，因此其右孩子还为被访问。所以接下来按照相同的规则对其右子树进行相同的处理，当访问完其右孩子时，
     * 该结点又出现在栈顶，此时可以将其出栈并访问。这样就保证了正确的访问顺序。可以看出，在这个过程中，每个结点都两次出现在栈顶，
     * 只有在第二次出现在栈顶时，才能访问它。因此需要多设置一个变量标识该结点是否是第一次出现在栈顶。
     * <p>
     * <p>
     * 下面分析一下这个代码：roor结点也就是A结点不为空也是首次访问该结点所以A.isFirst=true。把A压入到栈中依次的遍历左子树B,D执行的是
     * 同样的操作，执行完后栈从栈底到栈顶的数据依次为A,B,D。D的左子树为null,所以执行的是else的操作，当前结点弹出,弹出D,而且D.isFirst=true，
     * 此时是第二次的访问D结点所以D.isFirst=false,,此时还是把D结点压入到栈中，继续访问D的右结点G,由于G不为空所以G的G.isFirst=true,并且继续的遍历
     * G的左子树null,因为为null，所以把G结点弹出栈外，而且把G.isFirst=fasle,并且把G结点压入到栈中；此时遍历G的右子树，因为G的右子树也为null，所以
     * G结点弹栈，由于此时的G.isFirst=false所以执行sb.append,把G结点放在StringBuild里面去，node的值置为null,栈弹出栈顶元素D,由于D.isFirst=false，所以
     * 此时的sb中加入D结点，而且node=null；需要弹出当前结点B,由于B.isFirst=true;所以先压栈，然后把B.idFirst=false，然后遍历B的右子树为null，所以需要
     * sb中加入B元素，并把当前的node=null，弹出A结点，由于A的A.isFirst=true；所以A继续的压入到栈中，并且A.isFirst=false;继续的遍历A的右子树
     *
     * @return
     */
    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        LinkedList<Node<E>> stack = new LinkedList<>(); // 记录回退路径的工作栈
        Node<E> node = root;
        while (node != null || !stack.isEmpty()) { // 迭代条件
            if (node != null) { // 当前节点不为空
                node.isFirst = true; // 首次访问该节点，记为true
                stack.push(node); // 压栈操作
                node = node.left; // 继续遍历左子树
            } else { // 当前节点为空但工作栈不为空
                node = stack.pop(); // 当前节点弹栈
                if (node.isFirst) {
                    node.isFirst = false; // 第二次访问该节点,改为false
                    stack.push(node); // 只有在第三次才访问，因此，前节点再次压栈
                    node = node.right; // 访问该节点的右子树
                } else { // 第三次访问该节点
                    sb.append(node.data + " "); // 访问
                    node = null; // 当前节点的左子树、右子树及本身均已访问,需要访问工作栈中的节点
                }
            }
        }
        return sb.toString();
    }

    /**
     * 即先采用类似先序遍历，先遍历根结点再右孩子最后左孩子（先序是先根结点再左孩子最后右孩子），最后把遍历的序列逆转即得到了后序遍历
     *
     * @param root
     * @return
     */
    public String postOrderTraversal(Node<E> root) {
        LinkedList<Node<E>> stack = new LinkedList<>();
        stack.push(root);
        List<E> ret = new ArrayList<>();
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            if (node != null) {
                ret.add(node.data);
                stack.push(node.left);
                stack.push(node.right);
            }
        }
        Collections.reverse(ret);
        return ret.toString();
    }

    /**
     * 根据前序和中序遍历的结果，重构二叉树
     */
    public Node<E> createBinaryTreeByPreAndIn(String pre, String in) {
        if (pre.length() > 0) {
            //前序遍历的第一个结点就是根结点
            Node root = new Node(pre.charAt(0));
            //根结点在中序遍历结果中的位置
            int index = in.indexOf(pre.charAt(0));
            root.left = createBinaryTreeByPreAndIn(pre.substring(1, index + 1), in.substring(0, index));
            root.right = createBinaryTreeByPreAndIn(pre.substring(index + 1, pre.length()), in.substring(index + 1, in.length()));
            return root;
        }
        return null;
    }

    /**
     * 根据后序和中序，重构二叉树
     */
    public Node<E> createBinaryTreeByPostAndIn(String in, String post) {
        if (post.length() > 0) {
            Node root = new Node(post.charAt(post.length() - 1));
            int index = in.indexOf(post.charAt(post.length() - 1));
            root.left = createBinaryTreeByPostAndIn(in.substring(0, index), post.substring(0, index));
            root.right = createBinaryTreeByPreAndIn(in.substring(index + 1, in.length()), post.substring(index + 1, post.length() - 1));
        }
        return root;
    }

    /**
     * 以广义表的形式打印二叉树 前序遍历的应用"A(B(D(,G)),C(E,F))"
     * @param root
     * @return
     */
    public String printBinaryTree(Node<E> root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(root.data);
            if (root.left != null || root.right != null) {
                sb.append('(');
                sb.append(printBinaryTree(root.left));
                sb.append(',');
                sb.append(printBinaryTree(root.right));
                sb.append(')');
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryTree) {//先判断是否是一颗二叉树
            BinaryTree<E> tree = (BinaryTree<E>) obj;
            return equals0(this.root, tree.root);
        }
        return false;
    }

    /**
     * 判断两个二叉树是否是相等的
     *
     * @param src
     * @param des
     * @return
     */
    private boolean equals0(Node<E> src, Node<E> des) {
        if (src == null && des == null) { //如果两棵树都是空数，则他们是相等的
            return true;
        } else if (src == null || des == null) {//空树与非空的数不相等
            return false;
        } else {
            //判断两颗树是否是相等的：当前的结点是否是相等的，左子树是不是相等的  右子树是不是相等的
            return src.equals(des) && equals0(src.left, src.right) && equals0(src.right, des.right);
        }

    }

    /**
     * @description 后序遍历的思想：树的高度(空树为0)
     * @author rico
     * @created 2017年5月23日 下午12:00:08
     * @param root
     * @return
     */
    public int height(Node<E> root) {
        if (root != null) { // 递归终止条件
            int h1 = height(root.left);
            int h2 = height(root.right);
            return h1 > h2 ? h1 + 1 : h2 + 1;
        }
        return 0;
    }

    /**
     * 方法二:二叉树的高度：使用递归，时间复杂度O(n)
     * @param root
     * @return
     */
    public int height2(Node<E> root) {
        if (root != null) {
            // 左子树与右子树的高度取最大值加上当前节点
            return Math.max(height2(root.left),height2(root.right))+1;
        }
        return 0;
    }

    /**
     * 二叉树中节点的个数 ：使用递归的方式，如果只有根结点则结点数为1
     * 结点数：左子树个数+右子树的个数+1
     */
    public int size(Node<E> root) {
        if (root != null) {
            return size(root.left)+size(root.right)+1;
        }
        return 0;
    }
}
