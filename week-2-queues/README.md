91分

存在问题：

	Test 9: Create two nested iterators over the same randomized queue
	  * n = 10
	    - two inner iterators return the same sequence of items;
	    - they should return the same set of items but in a
	      different order
	
	  * n = 1000
	    - two inner iterators return the same sequence of items;
	    - they should return the same set of items but in a
	      different order
	
	==> FAILED
	
	Test 10: Create two parallel iterators over the same randomized queue
	  * n = 10
	    - two inner iterators return the same sequence of values;
	    - they should return the same set of values but in a
	      different order
	
	  * n = 1000
	    - two inner iterators return the same sequence of values;
	    - they should return the same set of values but in a
	      different order
	
	==> FAILED