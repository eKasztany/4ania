//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit

protocol DepartmentDetailViewDelegate: class {
    func didSelectDepartment(department: Department)
}

class DepartmentDetailView: UIView {

    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var openingHoursIconLabel: UILabel!
    @IBOutlet weak var openingHoursLabel: UILabel!
    @IBOutlet weak var phoneIcon: UILabel!
    @IBOutlet weak var phoneTextView: UITextView!

    weak var delegate: DepartmentDetailViewDelegate?

    override func awakeFromNib() {
        openingHoursIconLabel.font = UIFont.init(name: "FontAwesome", size: 20.0)!
        openingHoursIconLabel.text = "\u{f017}"
        phoneIcon.font = UIFont.init(name: "FontAwesome", size: 20.0)!
        phoneIcon.text = "\u{f095}"
    }

    var department: Department? {
        didSet {
            guard let department = department else { return }
            nameLabel.text = department.name
            openingHoursLabel.text = "\(department.openingHours.mon)\n"
                + "\(department.openingHours.tue)\n\(department.openingHours.wen)\n"
                + "\(department.openingHours.thr)\n\(department.openingHours.fri)"
            phoneTextView.text = department.phone
        }
    }

    @IBAction func goToService(_ sender: AnyObject) {
        guard let department = department else { return }
        delegate?.didSelectDepartment(department: department)
    }
}
