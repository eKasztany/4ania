//
//  Created by Aleksander Grzyb on 25/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit

class ServiceCollectionViewCell: UICollectionViewCell {
    @IBOutlet weak var serviceName: UILabel!
    @IBOutlet weak var imageView: UIImageView!

    var serviceGroup: ServiceGroup? {
        didSet {
            guard let serviceGroup = serviceGroup else { return }
            serviceName.text = serviceGroup.name
        }
    }

    func configure(service: ServiceGroup) {
        serviceName.text = service.name
    }

}
