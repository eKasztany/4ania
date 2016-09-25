//
//  Created by Aleksander Grzyb on 25/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit
import AlamofireImage
import Alamofire

class ServiceCollectionViewCell: UICollectionViewCell {
    @IBOutlet weak var serviceName: UILabel!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!

    var serviceGroup: ServiceGroup? {
        didSet {
            guard let serviceGroup = serviceGroup else { return }
            serviceName.text = serviceGroup.name
        }
    }

    func configure(service: ServiceGroup) {
        serviceName.text = service.name
        activityIndicator.startAnimating()
        Alamofire.request(service.photoUrl).responseImage { response in
            self.activityIndicator.stopAnimating()
            if let image = response.result.value {
                self.imageView.image = image
            }
        }
    }
}
