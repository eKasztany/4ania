//
//  Created by Aleksander Grzyb on 25/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit

class DialogViewController: UIViewController {

    @IBOutlet weak var numberLabel: UILabel!
    @IBOutlet weak var ticketLabel: UILabel!

    var ticketNumber: Int?
    var number: Int?

    override func viewDidLoad() {
        super.viewDidLoad()
        guard let ticketNumber = ticketNumber else { return }
        ticketLabel.text = "\(ticketNumber)"
        number = 1
    }

    @IBAction func stepperTapped(_ sender: UIStepper) {
        numberLabel.text = "\(Int(sender.value + 1))"
        number = Int(sender.value + 1)
    }
}
