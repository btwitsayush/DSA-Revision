
// Probelm-206 -> Reverse Linked List
// Problem link -> https://leetcode.com/problems/reverse-linked-list/


/*  Problem Statement
Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:

Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
Example 2:

Input: head = [1,2]
Output: [2,1]
Example 3:

Input: head = []
Output: []
 
Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000
*/



/*  Brute Solution

Intuition

Q) If we want to reverse something, what is the first data structure we can think of?
-> A stack, because it has the property to reverse anything.

So to reverse a linked list we can put value of all his nodes into a stack then we can access them in reverse order.


Step-1) Iterate over Linked List and put all of the list node value into stack

i.e ->  1->2->3->4->5

this will be our stack after iterating over linked list

   +-----+
   |  5  |
   +-----+
   |  4  |
   +-----+
   |  3  |
   +-----+
   |  2  |
   +-----+
   |  1  |
   +-----+

Step-2) Now we will start deleting the elements from top and we will get all the element is reverse order

5 4 3 2 1 


Time Complexity-O(2N) [O(N) to iterate over linked list and put all the elements into stack + O(N) to iterate over stack and put elements in reverse order]

Space Complexity - O(N) b/c we are using stack 

 */


// --> Code for LeetCode

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }

 class Solution {
     public ListNode reverseList(ListNode head) {
 
         // if there is no node or there exist only 1 node
         if(head==null || head.next==null){
             return head;
         }
         // to store value of list
         Stack<Integer> stack =new Stack<>();
         
         ListNode temp=head;
         //iterate over linked list and store value is stack 
         while(temp!=null){
             stack.push(temp.val);
             temp=temp.next;
         }
         // mover temp again to head to store the value in reverse order
         temp=head;
     // iterate over stack to get value in reverse order
         while(!stack.empty()){
             temp.val=stack.pop();
             temp=temp.next;
         }
         return head;
     }
 }
 */


//  Optimal  Solution

/*
 
Let look closely towards list given to us and reversed list

given list : 1--> 2--> 3--> 4--> 5-->null
reversed   : null<--1<--2<--3<--4<--5

if we notice we maked our first Node 1 to points towards null and then changed the direction of list form --> to <--

now how can we do that?

Let's divide our problems into chunks

chunk-1 what if we had no node in our list ?
If there is no list it means out list is empty we will return head (b/c head==null)

chunk-2 what if we had one node 1-->null?
If there is only one node we will return head b/c reverse of 1 node will be same

chunk-3 what if we had 2 nodes 1-->2-->null

Step-1) The first step we should do is to make our first node points towards null.
But the problem is we can't directly do that b/c if we do that directlt we will loose our link to Node 2 

Step-2) So before making our first Node points toward null we will access the address of our Node 2 


To perfrom above 2 steps we will need 2 pointers prev and next

prev will every time points towards null intially 

i.e  1-->2-->null

Intially
prev-->null
temp-->Head(1)
front--> We don' t know for now 

to make node 1 points towards null first we need to acces node 2
so, front =temp.next;

now we have access the node 2 we can make node 1 points towards null
temp.next=prev

null<--1  2-->null

prev-->null
temp-->head(1)
front-->temp.next(2)

now we need to attack Node 2 and Node 1

for Node 2 prev will be Node 1 and front will be null b/c we only have 2 nodes

prev==temp;

now temp will be node 2 to access node 2 we can use front

temp=front(2); 

now we want node2 (temp) to connect with prev(node 1) but before that we need to acces next element

front=temp.next(null)
connect node2 to node1

temp.next=prev;

Now we will move ahead to access next node but before moving our prev will also move forward

prev=temp

now move trmp forward to access next node
temp=front(null)

so here our temp reached null prev reached node2

temp reached null so we wills stop

our list became something like this

2-->1-->null  

TC-O(N) we are iterating only once over list
SC-O(1) b/c we are playing with links only

 */

// Optimal  Code

/* 

 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }

class Solution {
    public ListNode reverseList(ListNode head) {

        // if there is no node or there exist only 1 node
        if(head==null || head.next==null){
            return head;
        }

        ListNode prev=null;//to access previous node
        ListNode current=head;//current node

        while(current!=null){
            ListNode front=current.next ;//to access node next to current
            current.next=prev;
            prev=current;
            current=front;
        }
        
        return prev;
    }
}

*/
