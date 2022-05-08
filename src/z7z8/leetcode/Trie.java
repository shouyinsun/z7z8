package z7z8.leetcode;

/**
 * @author cash
 * @description
 * 字典树
 * 英文
 * @date 2022/4/3 7:21 PM
 */
public class Trie {
    //子节点
    Trie[] children;
    boolean isEnd;

    public Trie(){
        //26个字母
        children=new Trie[26];
        isEnd =false;
    }

    public void insert(String word){
        char[] chars =word.toCharArray();
        Trie trie =this;
        for (int i = 0; i < chars.length; i++) {
            //字符相减,确定index
            int index = chars[i] - 'a';
            if (null == trie.children[index]) {
                trie.children[index] = new Trie();
            }
            if(i == chars.length-1){
                trie.children[index].isEnd =true;
            }
            trie = trie.children[index];
        }
    }

    public boolean search(String word){
        char[] chars =word.toCharArray();
        Trie trie =this;
        for (int i = 0; i < chars.length; i++) {
            int index =chars[i] -'a';
            if(null == trie.children[index]){
                return false;
            }else{
                trie = trie.children[index];
            }
        }
        //isEnd,是结尾
        return trie.isEnd;
    }

    public boolean startWith(String prefix) {
        char[] chars =prefix.toCharArray();
        Trie trie =this;
        for (int i = 0; i < chars.length; i++) {
            int index =chars[i] -'a';
            if(null == trie.children[index]){
                return false;
            }else{
                trie = trie.children[index];
            }
        }
        //不用判断是否是结尾
        return true;
    }
}
