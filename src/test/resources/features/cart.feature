Feature: Verify Items on the cart and price

	@cart
	Scenario: Verify the items on the cart and price
		Given a customer adds following items to the cart
			| Stuffed Frog   | 5 |
			| Fluffy Bunny   | 2 |
			| Valentine Bear | 3 |
		Then sub total should be correct for all items
		And total price of items should be correct