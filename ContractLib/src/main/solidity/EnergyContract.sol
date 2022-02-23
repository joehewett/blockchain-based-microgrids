// SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.7.0;

contract EnergyContract {
    /* define variable greeting of the type string */
    string greet;

    int amountToBeSold = 0;
    int amountToBeBought = 0;

    mapping(address => int) private sellers;

    uint256 head = 0;
    uint256 tail = 0;
    address[50] orderedSellers;

    event SaleGoneThrough(address seller, address buyer, int amount);
    event AnnounceSaleIntention(address seller);

    int price = 1;

    function sellEnergy(int amount) public returns (bool) {
        require(amount > 0, "Amount must be larger than 0");
        amountToBeSold += amount;

        if (sellers[msg.sender] > 0)
            return false;

        orderedSellers[tail++] = msg.sender;
        sellers[msg.sender] = amount;

        emit AnnounceSaleIntention(msg.sender);
        return true;
    }

    // DO NOT change parameter names or types
    // IF YOU MUST please modify the task fixPayableBug in gradlew.build
    // Its inconvenient but necessary sorry
    function buyEnergy(int buyAmount) public payable {
        // TODO need to come up with better error handling
        require(buyAmount > 0, "Amount must be larger than 0");
        require(head != tail, "There arent any sellers");

        require(uint(buyAmount) <= msg.value, "Not provided enough cash");

        address seller = orderedSellers[head];
        int toBeSold = sellers[seller];
        if (toBeSold == buyAmount) {
            // Perfect match
            payable(seller).transfer(uint(buyAmount));
            head++;
            // TODO will break at 50
            delete sellers[seller];
            emit SaleGoneThrough(seller, msg.sender, buyAmount);
        } else if (toBeSold > buyAmount) {
            sellers[seller] = toBeSold - buyAmount;
            payable(seller).transfer(uint(buyAmount));
            emit SaleGoneThrough(seller, msg.sender, buyAmount);
        } else {
            // Not selling enough but well give some for now
            payable(seller).transfer(uint(toBeSold));
            delete sellers[seller];
            // will need to look for other buyers
            emit SaleGoneThrough(seller, msg.sender, toBeSold);
        }
    }

    function getPrice() public view returns (int) {
        return price;
    }

    function getToBeSold() public view returns (int) {
        return amountToBeSold;
    }

}
